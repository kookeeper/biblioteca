package br.com.msystem.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.SolicitarReservaDao;
import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.vo.SolicitarReserva;

@Controller
public class SolicitarReservaService extends GenericService<SolicitarReserva> {

	@Autowired
	public SolicitarReservaService(SolicitarReservaDao daoP) {
		super.dao = daoP;
	}

	@Override
	public int insertOrUpdate(final SolicitarReserva registro) throws SystemException {
		try {
			if (registro.getIdSolicitarReserva() == 0) {
				return dao.insert(registro);
			} else {
				return dao.update(registro);
			}
		} catch (DataIntegrityViolationException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}
}
