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
import br.com.msystem.biblioteca.form.BloquearMaterialBibliograficoForm;
import br.com.msystem.biblioteca.service.BloquearMaterialBibliograficoService;
import br.com.msystem.biblioteca.service.MaterialBibliograficoService;
import br.com.msystem.biblioteca.validator.BloquearMaterialBibliograficoValidator;
import br.com.msystem.biblioteca.vo.BloquearMaterialBibliografico;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class BloquearMaterialBibliograficoController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private BloquearMaterialBibliograficoService service;

	@Autowired
	private MaterialBibliograficoService materialBibliograficoService;

	@Autowired
	private BloquearMaterialBibliograficoValidator validator;

	@RequestMapping(value = "/editar.form", method = RequestMethod.GET)
	public String get(Integer idBloquearMaterialBibliografico, ModelMap model, HttpServletRequest request) {
		BloquearMaterialBibliograficoForm form = new BloquearMaterialBibliograficoForm();

		if (idBloquearMaterialBibliografico > 0) {
			BloquearMaterialBibliografico registro = service.buscarPorId(idBloquearMaterialBibliografico);
			form.setDataBloqueio(registro.getDataBloqueio());
			form.setIdBloquearMaterialBibliografico(registro.getIdBloquearMaterialBibliografico());
			form.setMaterialBibliografico(registro.getMaterialBibliografico());
			form.setIdUsuario(registro.getUsuario().getIdUsuario());
			form.setMotivoBloqueio(registro.getMotivoBloqueio());
			form.setStatusBloqueio(registro.getStatusBloqueioBoolean());
		} else {
			Integer idUsuario = ((Usuario) request.getSession().getAttribute("usuario")).getIdUsuario();
			form.setIdUsuario(idUsuario);
		}

		model.addAttribute("bloquearMaterialBibliograficoForm", form);
		return "editarBloquearMaterialBibliografico";
	}

	@RequestMapping(value = "/editar.form", method = RequestMethod.POST)
	public ModelAndView post(BloquearMaterialBibliograficoForm form, BindingResult result,
			SessionStatus status) {

		validator.validate(form, result);

		if (result.hasErrors()) {
			return new ModelAndView("editarBloquearMaterialBibliografico");
		} else {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(form.getIdUsuario());
			
			BloquearMaterialBibliografico registro = new BloquearMaterialBibliografico();
			registro.setDataBloqueio(form.getDataBloqueio());
			registro.setIdBloquearMaterialBibliografico(form.getIdBloquearMaterialBibliografico());
			registro.setMaterialBibliografico(form.getMaterialBibliografico());
			registro.setMotivoBloqueio(form.getMotivoBloqueio());
			registro.setStatusBloqueioBoolean(form.getStatusBloqueio());
			registro.setUsuario(usuario);

			try {
				service.insertOrUpdate(registro);
			} catch (SystemException e) {
				result.reject("error.insertOrUpdate");
				return new ModelAndView("editarBloquearMaterialBibliografico");
			}

			return new ModelAndView(new RedirectView("listar.form"));
		}
	}

	@ModelAttribute("listaMaterialBibliografico")
	public List<MaterialBibliografico> populateListaMaterialBibliografico() {
		return materialBibliograficoService.listar();
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

	@RequestMapping("/apagar.form")
	public ModelAndView apagar(Integer idBloquearMaterialBibliografico) {
		ModelAndView view = new ModelAndView(new RedirectView("listar.form"));
		try {
			service.delete(idBloquearMaterialBibliografico);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping("/listar.form")
	public String listar(ModelMap model) {
		return "listarBloquearMaterialBibliografico";
	}

	@ModelAttribute("listaBloquearMaterialBibliografico")
	public List<BloquearMaterialBibliografico> populateListaBloquearMaterialBibliografico() {
		return service.listar();
	}
}
