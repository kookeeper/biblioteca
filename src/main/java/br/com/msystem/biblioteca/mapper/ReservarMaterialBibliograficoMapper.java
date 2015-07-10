package br.com.msystem.biblioteca.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.service.MaterialBibliograficoService;
import br.com.msystem.biblioteca.service.SolicitarReservaService;
import br.com.msystem.biblioteca.service.UsuarioService;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;
import br.com.msystem.biblioteca.vo.ReservarMaterialBibliografico;
import br.com.msystem.biblioteca.vo.SolicitarReserva;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class ReservarMaterialBibliograficoMapper implements RowMapper<ReservarMaterialBibliografico> {

	@Autowired
	private MaterialBibliograficoService materialBibliograficoService;
	
	@Autowired
	private SolicitarReservaService solicitarReservaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public ReservarMaterialBibliografico mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaterialBibliografico materialBibliografico = materialBibliograficoService.buscarPorId(rs.getInt("id_material_bibliografico"));
		SolicitarReserva solicitarReserva = solicitarReservaService.buscarPorId(rs.getInt("id_solicitar_reserva"));
		Usuario usuario = usuarioService.buscarPorId(rs.getInt("id_usuario"));

		ReservarMaterialBibliografico registro = new ReservarMaterialBibliografico();
		registro.setDataInicioReserva(rs.getDate("data_inicio_reserva"));
		registro.setIdReservarMaterialBibliografico(rs.getInt("id_reservar_material_bibliografico"));
		registro.setMaterialBibliografico(materialBibliografico);
		registro.setObservacao(rs.getString("observacao"));
		registro.setPeriodoReserva(rs.getInt("periodo_reserva"));
		registro.setSolicitarReserva(solicitarReserva);
		registro.setStatusReserva(rs.getString("status_reserva"));
		registro.setUsuario(usuario);
		return registro;
	}

}
