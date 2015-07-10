package br.com.msystem.biblioteca.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.form.EditarLocalMaterialBibliograficoForm;
import br.com.msystem.biblioteca.service.LocalMaterialBibliograficoService;
import br.com.msystem.biblioteca.vo.LocalMaterialBibliografico;

@Controller
public class LocalMaterialBibliograficoController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private LocalMaterialBibliograficoService service;

	@RequestMapping(value = "/editar.form", method = RequestMethod.GET)
	public String get(Integer idLocalMaterialBibliografico, ModelMap model) {
		EditarLocalMaterialBibliograficoForm form = new EditarLocalMaterialBibliograficoForm();
		
		if (idLocalMaterialBibliografico > 0) {
			LocalMaterialBibliografico registro = service.buscarPorId(idLocalMaterialBibliografico);
			form.setDescricaoLocalMaterialBibliografico(registro.getDescricaoLocalMaterialBibliografico());
		}

		model.addAttribute("editarLocalMaterialBibliograficoForm", form);
		return "editarLocalMaterialBibliografico";
	}

	@RequestMapping(value = "/editar.form", method = RequestMethod.POST)
	public ModelAndView post(EditarLocalMaterialBibliograficoForm form, BindingResult result,
			SessionStatus status) {
		
		LocalMaterialBibliografico registro = new LocalMaterialBibliografico();
		registro.setIdLocalMaterialBibliografico(form.getIdLocalMaterialBibliografico());
		registro.setDescricaoLocalMaterialBibliografico(form.getDescricaoLocalMaterialBibliografico());

		service.insertOrUpdate(registro);

		return new ModelAndView(new RedirectView("listar.form"));
	}

	@RequestMapping("/apagar.form")
	public ModelAndView apagar(Integer idLocalMaterialBibliografico) {
		ModelAndView view = new ModelAndView(new RedirectView("listar.form"));
		try {
			service.delete(idLocalMaterialBibliografico);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping("/listar.form")
	public String listar(ModelMap model) {

		List<LocalMaterialBibliografico> lista = service.listar();

		model.addAttribute("listaLocalMaterialBibliografico", lista);
		return "listarLocalMaterialBibliografico";
	}

}
