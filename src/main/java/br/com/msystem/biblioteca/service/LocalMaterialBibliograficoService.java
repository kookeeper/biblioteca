package br.com.msystem.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.LocalMaterialBibliograficoDao;
import br.com.msystem.biblioteca.vo.LocalMaterialBibliografico;

@Controller
public class LocalMaterialBibliograficoService extends GenericService<LocalMaterialBibliografico> {

	@Autowired
	public LocalMaterialBibliograficoService(final LocalMaterialBibliograficoDao daoP) {
		super.dao = daoP;
	}
	
	@Override
	public int insertOrUpdate(LocalMaterialBibliografico registro) {
		if (registro.getIdLocalMaterialBibliografico() > 0) {
			return dao.update(registro);
		} else {
			return dao.insert(registro);
		}
	}

}
