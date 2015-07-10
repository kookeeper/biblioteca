package br.com.msystem.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.PermissaoDao;
import br.com.msystem.biblioteca.vo.Permissao;

@Controller
public class PermissaoService extends GenericService<Permissao> {

	@Autowired
	public PermissaoService(PermissaoDao daoP) {
		super.dao = daoP;
	}
	
	@Override
	public int insertOrUpdate(final Permissao registro) {
		if (registro.getIdPermissao() == null) {
			return dao.insert(registro);
		} else {
			return dao.update(registro);
		}
	}
}
