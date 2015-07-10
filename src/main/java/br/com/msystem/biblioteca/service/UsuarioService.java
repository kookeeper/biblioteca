package br.com.msystem.biblioteca.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.UsuarioDao;
import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class UsuarioService extends GenericService<Usuario> {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	public UsuarioService(UsuarioDao daoP) {
		super.dao = daoP;
	}
	
	@Override
	public int insertOrUpdate(Usuario usuario) throws SystemException {
		try {
			if (usuario.getIdUsuario() != null) {
				logger.info("Alterando login '" + usuario.getLogin() + "'.");
				return dao.update(usuario);
			} else {
				logger.info("Cadastrando login '" + usuario.getLogin() + "'.");
				return dao.insert(usuario);
			}
		} catch (DataIntegrityViolationException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}
}
