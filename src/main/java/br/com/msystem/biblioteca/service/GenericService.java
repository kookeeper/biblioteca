package br.com.msystem.biblioteca.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;

import br.com.msystem.biblioteca.dao.GenericDao;
import br.com.msystem.biblioteca.exception.SystemException;

public abstract class GenericService<T> {

	protected GenericDao<T> dao;

	public List<T> listar() {
		return dao.listar();
	}

	public T buscarPorId(Integer id) {
		return dao.buscarPorId(id);
	}

	abstract int insertOrUpdate(T registro) throws SystemException;

	public int delete(Integer id) throws SystemException {
		try {
			return dao.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new SystemException("Nao foi possivel excluir o registro, a integridade dos dados foi violada", e);
		}
	}

	public List<T> listar(Map<String, String> filtro, Boolean like) {
		return dao.listar(filtro, like);
	}

	public T buscar(Map<String, String> filtro) {
		return dao.buscar(filtro);
	}

	public T buscar(String campo, String valor) {
		Map<String, String> filtro = new HashMap<String, String>();
		filtro.put(campo, valor);
		return dao.buscar(filtro);
	}

}
