package br.com.msystem.biblioteca.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.msystem.biblioteca.service.LocalMaterialBibliograficoService;
import br.com.msystem.biblioteca.service.TipoMaterialBibliograficoService;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;

@Component
public class MaterialBibliograficoMapper implements RowMapper<MaterialBibliografico> {

	@Autowired
	private TipoMaterialBibliograficoService tipoMaterialBibliograficoService;

	@Autowired
	private LocalMaterialBibliograficoService localMaterialBibliograficoService;

	public MaterialBibliografico mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaterialBibliografico registro = new MaterialBibliografico();
		registro.setAutor(rs.getString("autor"));
		registro.setDataAquisicao(rs.getDate("data_aquisicao"));
		registro.setEdicao(rs.getString("edicao"));
		registro.setEditora(rs.getString("editora"));
		registro.setIdMaterialBibliografico(rs.getInt("id_material_bibliografico"));
		registro.setResenha(rs.getString("resenha"));
		registro.setTipoMaterialBibliografico(tipoMaterialBibliograficoService.buscarPorId(rs.getInt("id_tipo_material_bibliografico")));
		registro.setLocalMaterialBibliografico(localMaterialBibliograficoService.buscarPorId(rs.getInt("id_local_material_bibliografico")));
		registro.setTitulo(rs.getString("titulo"));
		registro.setCodigoMaterialBibliografico(rs.getString("codigo_material_bibliografico"));
		return registro;
	}
}
