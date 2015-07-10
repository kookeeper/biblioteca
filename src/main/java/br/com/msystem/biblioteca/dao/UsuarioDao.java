package br.com.msystem.biblioteca.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.mapper.UsuarioMapper;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class UsuarioDao extends GenericDao<Usuario> {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1804647252020786813L;

  @Autowired
  public UsuarioDao(UsuarioMapper mapperP) {
    super.mapper = mapperP;
  }

  @Override
  public int update(final Usuario usuario) {
    limparCache();
    logger.info("Atualizando usuario!");

    return getSimpleJdbcTemplate().update(
        "update usuario set " + "id_grupo_usuario = ?, " + "login = ?, "
            + "senha = ?, " + "bloqueado = ?, " + "motivo_bloqueio = ?, "
            + "email = ?, " + "nome_usuario = ? , " + "nome_usuario_chines = ? , "
            + "endereco = ? , " + "telefone = ? " + "where id_usuario = ?",
        new Object[] { usuario.getGrupoUsuario().getIdGrupoUsuario(),
            usuario.getLogin(), usuario.getSenha(), usuario.isBloqueado(),
            usuario.getMotivoBloqueio(), usuario.getEmail(),
            usuario.getNomeUsuario(), usuario.getNomeUsuarioChines(), 
            usuario.getEndereco(), usuario.getTelefone(), usuario.getIdUsuario() });
  }

  @Override
  public int insert(final Usuario usuario) {
    limparCache();
    logger.info("Inserindo usuario!");

    return getSimpleJdbcTemplate()
        .update(
            "insert into usuario (id_grupo_usuario, login, senha, data_cadastro, " +
            "bloqueado, motivo_bloqueio, email, nome_usuario, nome_usuario_chines, endereco, telefone) "
                + "values (?, ?, ?, current_date, ?, ?, ?, ?, ?, ?, ?)",
            new Object[] { usuario.getGrupoUsuario().getIdGrupoUsuario(),
                usuario.getLogin(), usuario.getSenha(), usuario.isBloqueado(),
                usuario.getMotivoBloqueio(), usuario.getEmail(),
                usuario.getNomeUsuario(), usuario.getNomeUsuarioChines(),
                usuario.getEndereco(), usuario.getTelefone() });
  }
}