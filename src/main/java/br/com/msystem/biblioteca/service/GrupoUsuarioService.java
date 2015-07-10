package br.com.msystem.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.GrupoUsuarioDao;
import br.com.msystem.biblioteca.vo.GrupoUsuario;

@Controller
public class GrupoUsuarioService extends GenericService<GrupoUsuario> {

	@Autowired
	public GrupoUsuarioService(GrupoUsuarioDao daoP) {
		super.dao = daoP;
	}
	
	@Override
	public int insertOrUpdate(final GrupoUsuario grupoUsuario) {
		if (grupoUsuario.getIdGrupoUsuario() == null) {
			return dao.insert(grupoUsuario);
		} else {
			return dao.update(grupoUsuario);
		}
	}
}
