package br.com.msystem.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.GrupoUsuarioPermissaoDao;
import br.com.msystem.biblioteca.vo.GrupoUsuarioPermissao;

@Controller
public class GrupoUsuarioPermissaoService extends GenericService<GrupoUsuarioPermissao> {

	@Autowired
	public GrupoUsuarioPermissaoService(GrupoUsuarioPermissaoDao daoP) {
		super.dao = daoP;
	}
	
	@Override
	public int insertOrUpdate(final GrupoUsuarioPermissao registro) {
		if (registro.getIdGrupoUsuarioPermissao() == null) {
			return dao.insert(registro);
		} else {
			return dao.update(registro);
		}
	}
}
