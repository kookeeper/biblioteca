package br.com.msystem.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.mapper.ReservarMaterialBibliograficoMapper;
import br.com.msystem.biblioteca.vo.ReservarMaterialBibliografico;

@Controller
public class ReservarMaterialBibliograficoDao extends
    GenericDao<ReservarMaterialBibliografico> {

  /**
	 * 
	 */
  private static final long serialVersionUID = -7696699505974200980L;

  @Autowired
  public ReservarMaterialBibliograficoDao(
      ReservarMaterialBibliograficoMapper mapperP) {
    super.mapper = mapperP;
  }

  @Override
  public int update(final ReservarMaterialBibliografico registro) {
    limparCache();
    logger.info("Atualizando reserva de material bibliografico.");

    Integer idSolicitarReserva = null;
    
    if (registro.getSolicitarReserva() != null) {
      idSolicitarReserva = registro.getSolicitarReserva().getIdSolicitarReserva();
    }

    return getSimpleJdbcTemplate().update(
        "update reservar_material_bibliografico set "
            + "data_inicio_reserva = ?, " + "id_material_bibliografico = ?, "
            + "observacao = ?, " + "periodo_reserva = ?, "
            + "id_solicitar_reserva = ?, " + "status_reserva = ?, "
            + "id_usuario = ? "
            + "where id_reservar_material_bibliografico = ? ",
        new Object[] { registro.getDataInicioReserva(),
            registro.getMaterialBibliografico().getIdMaterialBibliografico(),
            registro.getObservacao(), registro.getPeriodoReserva(),
            idSolicitarReserva,
            registro.getStatusReserva(), registro.getUsuario().getIdUsuario(),
            registro.getIdReservarMaterialBibliografico() });
  }

  @Override
  public int insert(final ReservarMaterialBibliografico registro) {
    limparCache();
    logger.info("Inserindo reserva de material bibliografico.");

    Integer idSolicitarReserva = null;
    
    if (registro.getSolicitarReserva() != null) {
      idSolicitarReserva = registro.getSolicitarReserva().getIdSolicitarReserva();
    }

    return getSimpleJdbcTemplate()
        .update(
            "insert into reservar_material_bibliografico (data_inicio_reserva, "
                + "id_material_bibliografico, observacao, periodo_reserva, id_solicitar_reserva, "
                + "status_reserva, id_usuario) "
                + "values (?, ?, ?, ?, ?, ?, ?)",
            new Object[] {
                registro.getDataInicioReserva(),
                registro.getMaterialBibliografico()
                    .getIdMaterialBibliografico(), registro.getObservacao(),
                registro.getPeriodoReserva(),
                idSolicitarReserva,
                registro.getStatusReserva(),
                registro.getUsuario().getIdUsuario() });
  }
  
  public List<ReservarMaterialBibliografico> listarReservaPendente() {
    String sql = "select * " +
    		"from reservar_material_bibliografico " +
    		"where status_reserva = 'ativo'";
    
    return getJdbcTemplate().query(sql, mapper);
  }
}