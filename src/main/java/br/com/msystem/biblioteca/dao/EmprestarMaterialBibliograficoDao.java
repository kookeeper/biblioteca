package br.com.msystem.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.mapper.EmprestarMaterialBibliograficoMapper;
import br.com.msystem.biblioteca.vo.EmprestarMaterialBibliografico;

@Controller
public class EmprestarMaterialBibliograficoDao extends
    GenericDao<EmprestarMaterialBibliografico> {

  /**
	 * 
	 */
  private static final long serialVersionUID = -2890709931911846727L;

  @Autowired
  public EmprestarMaterialBibliograficoDao(
      EmprestarMaterialBibliograficoMapper mapperP) {
    super.mapper = mapperP;
  }

  @Override
  public int update(final EmprestarMaterialBibliografico registro) {
    limparCache();
    logger.info("Atualizando emprestimo de material bibliografico.");

    return getSimpleJdbcTemplate()
        .update(
            "update emprestar_material_bibliografico set "
                + "data_devolucao_efetiva = ?, "
                + "data_devolucao_prevista = ?, "
                + "data_emprestimo = ?, "
                + "id_material_bibliografico = ?, "
                + "id_usuario = ? "
                + "where id_emprestar_material_bibliografico = ?",
            new Object[] {
                registro.getDataDevolucaoEfetiva(),
                registro.getDataDevolucaoPrevista(),
                registro.getDataEmprestimo(),
                registro.getMaterialBibliografico()
                    .getIdMaterialBibliografico(),
                registro.getUsuario().getIdUsuario(),
                registro.getIdEmprestarMaterialBibliografico() });
  }

  @Override
  public int insert(final EmprestarMaterialBibliografico registro) {
    limparCache();
    logger.info("Inserindo emprestimo de material bibliografico.");
    return getSimpleJdbcTemplate()
        .update(
            "insert into emprestar_material_bibliografico (data_devolucao_efetiva, data_devolucao_prevista, data_emprestimo, id_material_bibliografico, id_usuario) values (?, ?, ?, ?, ?)",
            new Object[] {
                registro.getDataDevolucaoEfetiva(),
                registro.getDataDevolucaoPrevista(),
                registro.getDataEmprestimo(),
                registro.getMaterialBibliografico()
                    .getIdMaterialBibliografico(),
                registro.getUsuario().getIdUsuario() });
  }
  
  public List<EmprestarMaterialBibliografico> listarEmprestimoPendente() {
    logger.info("Listando emprestimo de material bibliografico pendente.");
    
    String sql = "select * from emprestar_material_bibliografico where data_devolucao_efetiva is null order by data_devolucao_prevista desc";
    
    return getSimpleJdbcTemplate().query(sql, mapper);
  }
}