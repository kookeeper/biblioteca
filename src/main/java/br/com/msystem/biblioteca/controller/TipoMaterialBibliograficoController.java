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
import br.com.msystem.biblioteca.form.EditarTipoMaterialBibliograficoForm;
import br.com.msystem.biblioteca.service.TipoMaterialBibliograficoService;
import br.com.msystem.biblioteca.vo.TipoMaterialBibliografico;

@Controller
public class TipoMaterialBibliograficoController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private TipoMaterialBibliograficoService service;

	@RequestMapping(value = "/editar.form", method = RequestMethod.GET)
	public String get(Integer idTipoMaterialBibliografico, ModelMap model) {
		EditarTipoMaterialBibliograficoForm form = new EditarTipoMaterialBibliograficoForm();
		
		if (idTipoMaterialBibliografico > 0) {
			TipoMaterialBibliografico registro = service.buscarPorId(idTipoMaterialBibliografico);
			form.setDescricaoTipoMaterialBibliografico(registro.getDescricaoTipoMaterialBibliografico());
		}

		model.addAttribute("editarTipoMaterialBibliograficoForm", form);
		return "editarTipoMaterialBibliografico";
	}

	@RequestMapping(value = "/editar.form", method = RequestMethod.POST)
	public ModelAndView post(EditarTipoMaterialBibliograficoForm form, BindingResult result,
			SessionStatus status) {
		
		TipoMaterialBibliografico registro = new TipoMaterialBibliografico();
		registro.setIdTipoMaterialBibliografico(form.getIdTipoMaterialBibliografico());
		registro.setDescricaoTipoMaterialBibliografico(form.getDescricaoTipoMaterialBibliografico());

		service.insertOrUpdate(registro);

		return new ModelAndView(new RedirectView("listar.form"));
	}

	@RequestMapping("/apagar.form")
	public ModelAndView apagar(Integer idTipoMaterialBibliografico) {
		ModelAndView view = new ModelAndView(new RedirectView("listar.form"));
		try {
			service.delete(idTipoMaterialBibliografico);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping("/listar.form")
	public String listar(ModelMap model) {

		List<TipoMaterialBibliografico> lista = service.listar();

		model.addAttribute("listaTipoMaterialBibliografico", lista);
		return "listarTipoMaterialBibliografico";
	}

}
