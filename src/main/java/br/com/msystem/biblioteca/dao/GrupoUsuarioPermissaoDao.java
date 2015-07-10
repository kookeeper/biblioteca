package br.com.msystem.biblioteca.dao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.mapper.GrupoUsuarioPermissaoMapper;
import br.com.msystem.biblioteca.vo.GrupoUsuarioPermissao;

@Controller
public class GrupoUsuarioPermissaoDao extends GenericDao<GrupoUsuarioPermissao> {

  /**
	 * 
	 */
  private static final long serialVersionUID = 5452619781557332306L;

  @Autowired
  public GrupoUsuarioPermissaoDao(GrupoUsuarioPermissaoMapper mapperP) {
    super.mapper = mapperP;
  }

  @Override
  public int update(final GrupoUsuarioPermissao registro) {
    limparCache();
    logger.info("Atualizando usuario!");

    return getSimpleJdbcTemplate().update(
        "update grupo_usuario_permissao set " + "tipo_permissao = ? "
            + "where id_grupo_usuario_permissao = ?",
        new Object[] { registro.getIdGrupoUsuarioPermissao(),
            SetToString(registro.getTipoPermissao()) });
  }

  @Override
  public int insert(final GrupoUsuarioPermissao registro) {
    limparCache();
    logger.info("Inserindo permissao de grupo de usuario!");

    return getSimpleJdbcTemplate()
        .update(
            "insert into grupo_usuario_permissao (id_grupo_usuario, id_permissao, tipo_permissao) "
                + "values (?, ?, ?)",
            new Object[] { registro.getGrupoUsuario().getIdGrupoUsuario(),
                registro.getPermissao().getIdPermissao(),
                SetToString(registro.getTipoPermissao()) });
  }

  private String SetToString(Set<String> tipoPermissao) {
    String result = "";
    String and = "";
    for (String tipo : tipoPermissao) {
      result += and + tipo;
      and = ",";
    }
    return result;
  }

}