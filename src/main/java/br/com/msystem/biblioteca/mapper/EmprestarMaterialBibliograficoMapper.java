package br.com.msystem.biblioteca.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.service.MaterialBibliograficoService;
import br.com.msystem.biblioteca.service.UsuarioService;
import br.com.msystem.biblioteca.vo.EmprestarMaterialBibliografico;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class EmprestarMaterialBibliograficoMapper implements RowMapper<EmprestarMaterialBibliografico> {

	@Autowired
	private MaterialBibliograficoService materialBibliograficoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public EmprestarMaterialBibliografico mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaterialBibliografico materialBibliografico = materialBibliograficoService.buscarPorId(rs.getInt("id_material_bibliografico"));
		Usuario usuario = usuarioService.buscarPorId(rs.getInt("id_usuario"));

		EmprestarMaterialBibliografico registro = new EmprestarMaterialBibliografico();
		registro.setDataDevolucaoEfetiva(rs.getDate("data_devolucao_efetiva"));
		registro.setDataDevolucaoPrevista(rs.getDate("data_devolucao_prevista"));
		registro.setDataEmprestimo(rs.getDate("data_emprestimo"));
		registro.setIdEmprestarMaterialBibliografico(rs.getInt("id_emprestar_material_bibliografico"));
		registro.setMaterialBibliografico(materialBibliografico);
		registro.setUsuario(usuario);

		return registro;
	}

}
