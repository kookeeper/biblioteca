package br.com.msystem.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.ReservarMaterialBibliograficoDao;
import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.vo.ReservarMaterialBibliografico;

@Controller
public class ReservarMaterialBibliograficoService extends GenericService<ReservarMaterialBibliografico> {

	@Autowired
	public ReservarMaterialBibliograficoService(ReservarMaterialBibliograficoDao daoP) {
		super.dao = daoP;
	}

	@Override
	public int insertOrUpdate(final ReservarMaterialBibliografico registro) throws SystemException {
		try {
			if (registro.getIdReservarMaterialBibliografico() == 0) {
				return dao.insert(registro);
			} else {
				return dao.update(registro);
			}
		} catch (DataIntegrityViolationException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}
	
	public List<ReservarMaterialBibliografico> listarReservaPendente() {
	  return ((ReservarMaterialBibliograficoDao)dao).listarReservaPendente();
	}
}
