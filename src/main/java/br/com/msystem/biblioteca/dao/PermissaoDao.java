package br.com.msystem.biblioteca.dao;

import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.mapper.PermissaoMapper;
import br.com.msystem.biblioteca.vo.Permissao;

@Controller
public class PermissaoDao extends GenericDao<Permissao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1919053326275229335L;

	public PermissaoDao() {
		super.mapper = new PermissaoMapper();
	}

	@Override
	public int update(final Permissao registro) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int insert(final Permissao registro) {
		throw new UnsupportedOperationException();
	}

}