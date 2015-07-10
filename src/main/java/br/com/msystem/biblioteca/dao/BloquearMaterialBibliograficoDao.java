package br.com.msystem.biblioteca.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.mapper.BloquearMaterialBibliograficoMapper;
import br.com.msystem.biblioteca.vo.BloquearMaterialBibliografico;

@Controller
public class BloquearMaterialBibliograficoDao extends
    GenericDao<BloquearMaterialBibliografico> {

  /**
	 * 
	 */
  private static final long serialVersionUID = -2890709931911846727L;

  @Autowired
  public BloquearMaterialBibliograficoDao(
      BloquearMaterialBibliograficoMapper mapperP) {
    super.mapper = mapperP;
  }

  @Override
  public int update(final BloquearMaterialBibliografico registro) {
    limparCache();
    logger.info("Atualizando bloqueio de material bibliografico.");

    return getSimpleJdbcTemplate().update(
        "update bloquear_material_bibliografico set " + "data_bloqueio = ?, "
            + "id_material_bibliografico = ?, " + "motivo_bloqueio = ?, "
            + "status_bloqueio = ?, " + "id_usuario = ? "
            + "where id_bloquear_material_bibliografico = ? ",
        new Object[] { registro.getDataBloqueio(),
            registro.getMaterialBibliografico().getIdMaterialBibliografico(),
            registro.getMotivoBloqueio(), registro.getStatusBloqueio(),
            registro.getUsuario().getIdUsuario(),
            registro.getIdBloquearMaterialBibliografico() });
  }

  @Override
  public int insert(final BloquearMaterialBibliografico registro) {
    limparCache();
    logger.info("Inserindo bloqueio de material bibliografico.");
    return getSimpleJdbcTemplate()
        .update(
            "insert into bloquear_material_bibliografico (data_bloqueio, id_material_bibliografico, motivo_bloqueio, status_bloqueio, id_usuario) values (?, ?, ?, ?, ?)",
            new Object[] { registro.getDataBloqueio(),
                registro.getMaterialBibliografico().getIdMaterialBibliografico(),
                registro.getMotivoBloqueio(), registro.getStatusBloqueio(),
                registro.getUsuario().getIdUsuario()});
  }
}