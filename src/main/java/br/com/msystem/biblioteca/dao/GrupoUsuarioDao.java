package br.com.msystem.biblioteca.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.mapper.GrupoUsuarioMapper;
import br.com.msystem.biblioteca.vo.GrupoUsuario;

@Controller
public class GrupoUsuarioDao extends GenericDao<GrupoUsuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1020604613857132193L;

	@Autowired
	public GrupoUsuarioDao(GrupoUsuarioMapper mapperP) {
		super.mapper = mapperP;
	}

	@Override
	public int update(final GrupoUsuario grupoUsuario) {
    limparCache();
		logger.info("Atualizando grupo de usuario!");
		return getSimpleJdbcTemplate().update(
				"update grupo_usuario set descricao_grupo_usuario = ? where id_grupo_usuario = ?",
				new Object[] {grupoUsuario.getDescricaoGrupoUsuario(), grupoUsuario.getIdGrupoUsuario() });
	}

	@Override
	public int insert(final GrupoUsuario grupoUsuario) {
    limparCache();
		logger.info("Inserindo grupo de usuario!");
		return getSimpleJdbcTemplate()
				.update("insert into grupo_usuario (descricao_grupo_usuario) values (?)",
				    new Object[] {grupoUsuario.getDescricaoGrupoUsuario()});
	}
}