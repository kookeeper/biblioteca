package br.com.msystem.biblioteca.dao;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import br.com.msystem.biblioteca.util.CacheUtil;
import br.com.msystem.biblioteca.util.CacheUtil.CacheAction;

@SuppressWarnings("restriction")
public abstract class GenericDao<T> extends JdbcDaoSupport implements
    Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1760097830010386875L;

  private String tabela;

  private String campoId;
  
  private String cacheName;

  protected RowMapper<T> mapper;
  
  protected String getTabela() {
    return tabela;
  }

  @Autowired
  public void setDataSource(BasicDataSource dataSource) {
    super.setDataSource(dataSource);
  }

  protected JdbcTemplate getSimpleJdbcTemplate() {

    getJdbcTemplate().execute("SET character_set_client=big5");
    getJdbcTemplate().execute("SET character_set_connection=big5");

    return getJdbcTemplate();
  }

  public GenericDao() {
    ParameterizedTypeImpl impl = (ParameterizedTypeImpl) getClass()
        .getGenericSuperclass();
    Type voType = impl.getActualTypeArguments()[0];
    try {
      Class<?> voClass = Class.forName(voType.toString().replaceAll("class ",
          ""));
      javax.persistence.Table table = (Table) voClass.getDeclaredAnnotations()[1];
      tabela = table.name();
      cacheName = tabela + "Cache";

      Method metodoId = null;
      Method[] metodos = voClass.getMethods();
      for (Method metodo : metodos) {
        Annotation[] anotacoes = metodo.getAnnotations();
        for (Annotation anotacao : anotacoes) {
          if (anotacao instanceof javax.persistence.Id) {
            metodoId = metodo;
          }
        }
      }

      if (metodoId != null) {
        Annotation[] anotacoes = metodoId.getAnnotations();
        for (Annotation anotacao : anotacoes) {
          if (anotacao instanceof javax.persistence.Column) {
            campoId = ((javax.persistence.Column) anotacao).name();
          }
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public List<T> listar() {
    return listar(new HashMap<String, String>(), false);
  }

  @SuppressWarnings("unchecked")
  public T buscarPorId(final Integer id) {
    logger.info("Pesquisando registro com id = " + id + " no cache " + tabela);

    T registro = (T) CacheUtil.getCacheValue(tabela + "Cache", id, new CacheUtil.CacheAction() {
      
      @Override
      public Object execute() {
        try {
          logger.info("Pesquisando registro com id = " + id + " na tabela " + tabela);
          T registro = (T) getSimpleJdbcTemplate().queryForObject(
              "select * from " + tabela + " where " + campoId + " = ?", mapper, id);
          return registro;
        } catch (EmptyResultDataAccessException e) {
          return null;
        }
      }
    });

    return registro;
  }

  public abstract int update(final T registro);

  public abstract int insert(final T registro);

  public int delete(final Integer id) {

    limparCache();

    logger.info("Apagando registro na tabela " + tabela + ": '" + id + "'!");
    int registros = getSimpleJdbcTemplate().update(
        "delete from " + tabela + " where " + campoId + " = ?", id);
    return registros;

  }

  protected void limparCache() {
    CacheUtil.getCache(cacheName).removeAll();
    CacheUtil.getCache(cacheName + false).removeAll();
    CacheUtil.getCache(cacheName + true).removeAll();
  }

  @SuppressWarnings("unchecked")
  public T buscar(final Map<String, String> filtro) {
    logger.info("Pesquisando registros no cache " + tabela);
    
    T resultado = (T) CacheUtil.getCacheValue(tabela + "Cache", filtro, new CacheUtil.CacheAction() {
      
      @Override
      public Object execute() {
        logger.info("Pesquisando registros na tabela " + tabela);
        String sql = "select * from " + tabela + " ";
        String and = " where ";
        List<Object> valores = new ArrayList<Object>();

        for (Map.Entry<String, String> item : filtro.entrySet()) {
          sql += and + item.getKey() + " = ?";
          and = " and ";
          valores.add(item.getValue());
        }
        try {
          return (T) getSimpleJdbcTemplate().queryForObject(sql, mapper, valores.toArray());
        } catch (EmptyResultDataAccessException e) {
          return null;
        }
      }
    });
    
    return resultado;
  }

  @SuppressWarnings("unchecked")
  public List<T> listar(final Map<String, String> filtro, final Boolean like) {
    logger.info("Listando registros do cache " + tabela);
    
    List<T> lista = (List<T>) CacheUtil.getCacheValue(tabela + "Cache" + like, filtro, new CacheAction() {
      
      @Override
      public Object execute() {
        logger.info("Listando registros da tabela " + tabela);
        String sql = "select * from " + tabela + " ";
        String and = " where ";
        List<Object> valores = new ArrayList<Object>();

        for (Map.Entry<String, String> item : filtro.entrySet()) {
          if (like) {
            sql += and + item.getKey() + " like '%?%'";
          } else {
            sql += and + item.getKey() + " = ?";
          }
          and = " and ";
          valores.add(item.getValue());
        }
        sql += " order by 1";

        return (List<T>) getSimpleJdbcTemplate().query(sql, mapper, valores.toArray());
      }
    });
    
    return lista;
  }
}
