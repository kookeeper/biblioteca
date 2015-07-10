package br.com.msystem.biblioteca.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.service.MaterialBibliograficoService;
import br.com.msystem.biblioteca.service.UsuarioService;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;
import br.com.msystem.biblioteca.vo.SolicitarReserva;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class SolicitarReservaMapper implements RowMapper<SolicitarReserva> {

	@Autowired
	private MaterialBibliograficoService materialBibliograficoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public SolicitarReserva mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaterialBibliografico materialBibliografico = materialBibliograficoService.buscarPorId(rs.getInt("id_material_bibliografico"));
		Usuario usuario = usuarioService.buscarPorId(rs.getInt("id_usuario"));

		SolicitarReserva registro = new SolicitarReserva();
		registro.setDataSolicitacao(rs.getDate("data_solicitacao"));
		registro.setIdSolicitarReserva(rs.getInt("id_solicitar_reserva"));
		registro.setMaterialBibliografico(materialBibliografico);
		registro.setObservacao(rs.getString("observacao"));
		registro.setUsuario(usuario);
		return registro;
	}

}
