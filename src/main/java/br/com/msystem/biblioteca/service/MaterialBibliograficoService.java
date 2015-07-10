package br.com.msystem.biblioteca.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.MaterialBibliograficoDao;
import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;

@Controller
public class MaterialBibliograficoService extends GenericService<MaterialBibliografico> {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	public MaterialBibliograficoService(MaterialBibliograficoDao daoP) {
		super.dao = daoP;
	}
	
	@Override
	public int insertOrUpdate(MaterialBibliografico registro) throws SystemException {
		try {
			if (registro.getIdMaterialBibliografico() > 0) {
				logger.info("Alterando registro '" + registro.getTitulo() + "'.");
				return dao.update(registro);
			} else {
				logger.info("Cadastrando registro '" + registro.getTitulo() + "'.");
				return dao.insert(registro);
			}
		} catch (DataIntegrityViolationException e) {
			throw new SystemException("Nao foi possivel inserir/alterar a base de dados.", e);
		}
	}
	
	public List<MaterialBibliografico> listarDisponivel() {
	  return ((MaterialBibliograficoDao)dao).listarDisponivel();
	}
}
