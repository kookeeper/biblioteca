package br.com.msystem.biblioteca.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.msystem.biblioteca.service.GrupoUsuarioService;
import br.com.msystem.biblioteca.vo.Usuario;

@Component
@Resource(name="UsuarioMapper")
public class UsuarioMapper implements RowMapper<Usuario> {

	@Autowired
	private GrupoUsuarioService grupoUsuarioService;

	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario registro = new Usuario();
		registro.setIdUsuario(rs.getInt("id_usuario"));
		registro.setLogin(rs.getString("login"));
		registro.setSenha(rs.getString("senha"));
		registro.setDataCadastro(rs.getDate("data_cadastro"));
		registro.setBloqueado(rs.getBoolean("bloqueado"));
		registro.setMotivoBloqueio(rs.getString("motivo_bloqueio"));
		registro.setEmail(rs.getString("email"));
		registro.setNomeUsuario(rs.getString("nome_usuario"));
		registro.setNomeUsuarioChines(rs.getString("nome_usuario_chines"));
		registro.setTelefone(rs.getString("telefone"));
		registro.setEndereco(rs.getString("endereco"));

		registro.setGrupoUsuario(grupoUsuarioService.buscarPorId(rs
				.getInt("id_grupo_usuario")));

		return registro;
	}
}
