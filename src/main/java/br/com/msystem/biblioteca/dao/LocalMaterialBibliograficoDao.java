package br.com.msystem.biblioteca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.vo.LocalMaterialBibliografico;

@Controller
public class LocalMaterialBibliograficoDao extends
    GenericDao<LocalMaterialBibliografico> {


  /**
	 * 
	 */
	private static final long serialVersionUID = -7845568599235448957L;

public LocalMaterialBibliograficoDao() {
    super.mapper = new InnerMapper();
  }

  @Override
  public int update(LocalMaterialBibliografico registro) {
    limparCache();
    logger.info("Atualizando local de material bibliografico.");
    return getSimpleJdbcTemplate().update(
        "update local_material_bibliografico "
            + "set descricao_local_material_bibliografico = ? "
            + "where id_local_material_bibliografico = ?",
        new Object[] { registro.getDescricaoLocalMaterialBibliografico(),
            registro.getIdLocalMaterialBibliografico() });
  }

  @Override
  public int insert(LocalMaterialBibliografico registro) {
    limparCache();
    logger.info("Inserindo local de material bibliografico.");
    return getSimpleJdbcTemplate()
        .update(
            "insert into local_material_bibliografico (descricao_local_material_bibliografico) values (?)",
            new Object[] { registro.getDescricaoLocalMaterialBibliografico() });
  }

  private static class InnerMapper implements
      RowMapper<LocalMaterialBibliografico> {

    public LocalMaterialBibliografico mapRow(ResultSet rs, int rowNum)
        throws SQLException {
    	LocalMaterialBibliografico registro = new LocalMaterialBibliografico();
      registro.setIdLocalMaterialBibliografico(rs
          .getInt("id_local_material_bibliografico"));
      registro.setDescricaoLocalMaterialBibliografico(rs
          .getString("descricao_local_material_bibliografico"));
      return registro;
    }

  }
}
