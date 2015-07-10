package br.com.msystem.biblioteca.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.service.MaterialBibliograficoService;
import br.com.msystem.biblioteca.service.UsuarioService;
import br.com.msystem.biblioteca.vo.BloquearMaterialBibliografico;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class BloquearMaterialBibliograficoMapper implements RowMapper<BloquearMaterialBibliografico> {

	@Autowired
	private MaterialBibliograficoService materialBibliograficoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public BloquearMaterialBibliografico mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaterialBibliografico materialBibliografico = materialBibliograficoService.buscarPorId(rs.getInt("id_material_bibliografico"));
		Usuario usuario = usuarioService.buscarPorId(rs.getInt("id_usuario"));

		BloquearMaterialBibliografico registro = new BloquearMaterialBibliografico();
		registro.setDataBloqueio(rs.getDate("data_bloqueio"));
		registro.setIdBloquearMaterialBibliografico(rs.getInt("id_bloquear_material_bibliografico"));
		registro.setMaterialBibliografico(materialBibliografico);
		registro.setMotivoBloqueio(rs.getString("motivo_bloqueio"));
		registro.setStatusBloqueio(rs.getString("status_bloqueio"));
		registro.setUsuario(usuario);
		return registro;
	}

}
