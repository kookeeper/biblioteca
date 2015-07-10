package br.com.msystem.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.TipoMaterialBibliograficoDao;
import br.com.msystem.biblioteca.vo.TipoMaterialBibliografico;

@Controller
public class TipoMaterialBibliograficoService extends GenericService<TipoMaterialBibliografico> {

	@Autowired
	public TipoMaterialBibliograficoService(final TipoMaterialBibliograficoDao daoP) {
		super.dao = daoP;
	}
	
	@Override
	public int insertOrUpdate(TipoMaterialBibliografico registro) {
		if (registro.getIdTipoMaterialBibliografico() > 0) {
			return dao.update(registro);
		} else {
			return dao.insert(registro);
		}
	}

}
