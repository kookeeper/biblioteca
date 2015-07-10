package br.com.msystem.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.BloquearMaterialBibliograficoDao;
import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.vo.BloquearMaterialBibliografico;

@Controller
public class BloquearMaterialBibliograficoService extends GenericService<BloquearMaterialBibliografico> {

	@Autowired
	public BloquearMaterialBibliograficoService(BloquearMaterialBibliograficoDao daoP) {
		super.dao = daoP;
	}

	@Override
	public int insertOrUpdate(final BloquearMaterialBibliografico registro) throws SystemException {
		try {
			if (registro.getIdBloquearMaterialBibliografico() == 0) {
				return dao.insert(registro);
			} else {
				return dao.update(registro);
			}
		} catch (DataIntegrityViolationException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}
}
