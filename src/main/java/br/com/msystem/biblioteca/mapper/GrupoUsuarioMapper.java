package br.com.msystem.biblioteca.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.service.GrupoUsuarioPermissaoService;
import br.com.msystem.biblioteca.vo.GrupoUsuario;
import br.com.msystem.biblioteca.vo.GrupoUsuarioPermissao;

@Controller
public class GrupoUsuarioMapper implements RowMapper<GrupoUsuario> {

	@Autowired
	private GrupoUsuarioPermissaoService grupoUsuarioPermissaoService;

	public GrupoUsuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		GrupoUsuario registro = new GrupoUsuario();
		registro.setIdGrupoUsuario(rs.getInt("id_grupo_usuario"));
		registro.setDescricaoGrupoUsuario(rs.getString("descricao_grupo_usuario"));
		registro.setGrupoUsuarioPermissaos(getPermissoes(registro.getIdGrupoUsuario()));
		return registro;
	}

	private Set<GrupoUsuarioPermissao> getPermissoes(Integer id_grupo_usuario) {
		Set<GrupoUsuarioPermissao> result = new HashSet<GrupoUsuarioPermissao>();
		
		Map<String, String> filtro = new HashMap<String, String>();
		filtro.put("id_grupo_usuario", String.valueOf(id_grupo_usuario));
		result.addAll(grupoUsuarioPermissaoService.listar(filtro, false));
		return result;
	}

}
