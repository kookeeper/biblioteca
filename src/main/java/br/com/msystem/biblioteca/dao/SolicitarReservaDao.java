package br.com.msystem.biblioteca.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.mapper.SolicitarReservaMapper;
import br.com.msystem.biblioteca.vo.SolicitarReserva;

@Controller
public class SolicitarReservaDao extends GenericDao<SolicitarReserva> {

  /**
	 * 
	 */
  private static final long serialVersionUID = -1020604613857132193L;

  @Autowired
  public SolicitarReservaDao(SolicitarReservaMapper mapperP) {
    super.mapper = mapperP;
  }

  @Override
  public int update(final SolicitarReserva registro) {
    limparCache();
    logger.info("Atualizando solicitacao de reserva.");
    return getSimpleJdbcTemplate().update(
        "update solicitar_reserva set " + "id_usuario = ?, "
            + "id_material_bibliografico = ?, " + "data_solicitacao = ?, "
            + "observacao = ? " + "where id_solicitar_reserva = ?",
        new Object[] { registro.getUsuario().getIdUsuario(),
            registro.getMaterialBibliografico().getIdMaterialBibliografico(),
            registro.getDataSolicitacao(), registro.getObservacao(),
            registro.getIdSolicitarReserva() });
  }

  @Override
  public int insert(final SolicitarReserva registro) {
    limparCache();
    logger.info("Inserindo solicitacao de reserva.");
    return getSimpleJdbcTemplate()
        .update(
            "insert into solicitar_reserva (id_usuario, id_material_bibliografico, data_solicitacao, observacao) values (?, ?, ?, ?)",
            new Object[] {
                registro.getUsuario().getIdUsuario(),
                registro.getMaterialBibliografico()
                    .getIdMaterialBibliografico(),
                registro.getDataSolicitacao(), registro.getObservacao() });
  }
}