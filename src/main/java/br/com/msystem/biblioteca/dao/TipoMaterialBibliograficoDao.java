package br.com.msystem.biblioteca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.vo.TipoMaterialBibliografico;

@Controller
public class TipoMaterialBibliograficoDao extends
    GenericDao<TipoMaterialBibliografico> {

  /**
	 * 
	 */
  private static final long serialVersionUID = -4043507768904113218L;

  public TipoMaterialBibliograficoDao() {
    super.mapper = new InnerMapper();
  }

  @Override
  public int update(TipoMaterialBibliografico registro) {
    limparCache();
    logger.info("Atualizando tipo de material bibliografico.");
    return getSimpleJdbcTemplate().update(
        "update tipo_material_bibliografico "
            + "set descricao_tipo_material_bibliografico = ? "
            + "where id_tipo_material_bibliografico = ?",
        new Object[] { registro.getDescricaoTipoMaterialBibliografico(),
            registro.getIdTipoMaterialBibliografico() });
  }

  @Override
  public int insert(TipoMaterialBibliografico registro) {
    limparCache();
    logger.info("Inserindo tipo de material bibliografico.");
    return getSimpleJdbcTemplate()
        .update(
            "insert into tipo_material_bibliografico (descricao_tipo_material_bibliografico) values (?)",
            new Object[] { registro.getDescricaoTipoMaterialBibliografico() });
  }

  private static class InnerMapper implements
      RowMapper<TipoMaterialBibliografico> {

    public TipoMaterialBibliografico mapRow(ResultSet rs, int rowNum)
        throws SQLException {
      TipoMaterialBibliografico registro = new TipoMaterialBibliografico();
      registro.setIdTipoMaterialBibliografico(rs
          .getInt("id_tipo_material_bibliografico"));
      registro.setDescricaoTipoMaterialBibliografico(rs
          .getString("descricao_tipo_material_bibliografico"));
      return registro;
    }

  }
}
