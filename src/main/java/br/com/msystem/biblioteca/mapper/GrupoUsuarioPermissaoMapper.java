package br.com.msystem.biblioteca.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.service.PermissaoService;
import br.com.msystem.biblioteca.vo.GrupoUsuario;
import br.com.msystem.biblioteca.vo.GrupoUsuarioPermissao;

@Controller
public class GrupoUsuarioPermissaoMapper implements
		RowMapper<GrupoUsuarioPermissao> {

	@Autowired
	private PermissaoService permissaoService;

	public GrupoUsuarioPermissao mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		GrupoUsuarioPermissao registro = new GrupoUsuarioPermissao();
		registro.setIdGrupoUsuarioPermissao(rs
				.getInt("id_grupo_usuario_permissao"));
		registro.setPermissao(permissaoService.buscarPorId(rs.getInt("id_permissao")));
		registro.setTipoPermissao(StringToSet(rs.getString("tipo_permissao")));

		GrupoUsuario grupoUsuario = new GrupoUsuario();
		grupoUsuario.setIdGrupoUsuario(rs.getInt("id_grupo_usuario"));

		registro.setGrupoUsuario(grupoUsuario);
		return registro;
	}

	private Set<String> StringToSet(String string) {
		Set<String> result = new HashSet<String>();
		result.addAll(Arrays.asList(string.split(",")));
		return result;
	}
}
