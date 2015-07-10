package br.com.msystem.biblioteca.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.form.EditarMaterialBibliograficoForm;
import br.com.msystem.biblioteca.service.LocalMaterialBibliograficoService;
import br.com.msystem.biblioteca.service.MaterialBibliograficoService;
import br.com.msystem.biblioteca.service.TipoMaterialBibliograficoService;
import br.com.msystem.biblioteca.validator.MaterialBibliograficoValidator;
import br.com.msystem.biblioteca.vo.LocalMaterialBibliografico;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;
import br.com.msystem.biblioteca.vo.TipoMaterialBibliografico;

@Controller
public class MaterialBibliograficoController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private MaterialBibliograficoService service;
	
	@Autowired
	private TipoMaterialBibliograficoService tipoMaterialBibliograficoService;
	
	@Autowired
	private LocalMaterialBibliograficoService localMaterialBibliograficoService;
	
	@Autowired
	private MaterialBibliograficoValidator validator;

	@RequestMapping(value = "/editar.form", method = RequestMethod.GET)
	public String get(Integer idMaterialBibliografico, ModelMap model) {
		EditarMaterialBibliograficoForm form = new EditarMaterialBibliograficoForm();
		
		if (idMaterialBibliografico > 0) {
			MaterialBibliografico registro = service.buscarPorId(idMaterialBibliografico);
			form.setAutor(registro.getAutor());
			form.setDataAquisicao(registro.getDataAquisicao());
			form.setEdicao(registro.getEdicao());
			form.setEditora(registro.getEditora());
			form.setIdMaterialBibliografico(registro.getIdMaterialBibliografico());
			form.setIdTipoMaterialBibliografico(registro.getTipoMaterialBibliografico().getIdTipoMaterialBibliografico());
			form.setIdLocalMaterialBibliografico(registro.getLocalMaterialBibliografico().getIdLocalMaterialBibliografico());
			form.setResenha(registro.getResenha());
			form.setTitulo(registro.getTitulo());
			form.setCodigoMaterialBibliografico(registro.getCodigoMaterialBibliografico());
		}

		model.addAttribute("editarMaterialBibliograficoForm", form);
		return "editarMaterialBibliografico";
	}

	@RequestMapping(value = "/editar.form", method = RequestMethod.POST)
	public ModelAndView post(EditarMaterialBibliograficoForm form, BindingResult result,
			SessionStatus status) {
		
		validator.validate(form, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("editarMaterialBibliografico");
		} else {
			TipoMaterialBibliografico tipoMaterialBibliografico = new TipoMaterialBibliografico();
			tipoMaterialBibliografico.setIdTipoMaterialBibliografico(form.getIdTipoMaterialBibliografico());
			
			LocalMaterialBibliografico localMaterialBibliografico = new LocalMaterialBibliografico();
			localMaterialBibliografico.setIdLocalMaterialBibliografico(form.getIdLocalMaterialBibliografico());

			MaterialBibliografico registro = new MaterialBibliografico();
			registro.setAutor(form.getAutor());
			registro.setDataAquisicao(form.getDataAquisicao());
			registro.setEdicao(form.getEdicao());
			registro.setEditora(form.getEditora());
			registro.setIdMaterialBibliografico(form.getIdMaterialBibliografico());
			registro.setResenha(form.getResenha());
			registro.setTipoMaterialBibliografico(tipoMaterialBibliografico);
			registro.setLocalMaterialBibliografico(localMaterialBibliografico);
			registro.setTitulo(form.getTitulo());
			registro.setCodigoMaterialBibliografico(form.getCodigoMaterialBibliografico());

			try {
				service.insertOrUpdate(registro);
				return new ModelAndView(new RedirectView("listar.form"));
			} catch (SystemException e) {
				ModelAndView view = new ModelAndView(new RedirectView("editar.form"));
				view.getModel().put("idMaterialBibliografico", form.getIdMaterialBibliografico());
				view.getModel().put("error", e.getMessage());
				logger.error(e.getMessage(), e);
				result.reject("error.insertOrUpdate");
				return new ModelAndView("editarMaterialBibliografico");
			}
		}
	}

	@RequestMapping("/apagar.form")
	public ModelAndView apagar(Integer idMaterialBibliografico) {
		ModelAndView view = new ModelAndView(new RedirectView("listar.form"));
		try {
			service.delete(idMaterialBibliografico);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping(value = "/listar.form")
	public String listar(ModelMap model) {
		
		List<MaterialBibliografico> lista = service.listar();

		model.addAttribute("listaMaterialBibliografico", lista);
		return "listarMaterialBibliografico";
	}

	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	@ModelAttribute("listaTipoMaterialBibliografico")
	public List<TipoMaterialBibliografico> populateListaTipoMaterialBibliografico() {
		return tipoMaterialBibliograficoService.listar();
	}

	@ModelAttribute("listaLocalMaterialBibliografico")
	public List<LocalMaterialBibliografico> populateListaLocalMaterialBibliografico() {
		return localMaterialBibliograficoService.listar();
	}

}
