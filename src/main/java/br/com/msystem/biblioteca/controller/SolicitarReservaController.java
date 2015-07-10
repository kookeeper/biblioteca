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
import br.com.msystem.biblioteca.form.SolicitarReservaForm;
import br.com.msystem.biblioteca.service.MaterialBibliograficoService;
import br.com.msystem.biblioteca.service.SolicitarReservaService;
import br.com.msystem.biblioteca.validator.SolicitarReservaValidator;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;
import br.com.msystem.biblioteca.vo.SolicitarReserva;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class SolicitarReservaController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SolicitarReservaService service;
	
	@Autowired
	private MaterialBibliograficoService materialBibliograficoService;

	@Autowired
	private SolicitarReservaValidator validator;

	@RequestMapping(value = "/editar.form", method = RequestMethod.GET)
	public String get(Integer idSolicitarReserva, ModelMap model, HttpServletRequest request) {
		SolicitarReservaForm form = new SolicitarReservaForm();

		if (idSolicitarReserva > 0) {
			SolicitarReserva registro = service.buscarPorId(idSolicitarReserva);
			form.setDataSolicitacao(registro.getDataSolicitacao());
			form.setIdMaterialBibliografico(registro.getMaterialBibliografico().getIdMaterialBibliografico());
			form.setIdSolicitarReserva(registro.getIdSolicitarReserva());
			form.setIdUsuario(registro.getUsuario().getIdUsuario());
			form.setObservacao(registro.getObservacao());
		} else {
			Integer idUsuario = ((Usuario) request.getSession().getAttribute("usuario")).getIdUsuario();
			form.setIdUsuario(idUsuario);
		}

		model.addAttribute("editarSolicitarReservaForm", form);
		return "editarSolicitarReserva";
	}

	@RequestMapping(value = "/editar.form", method = RequestMethod.POST)
	public ModelAndView post(SolicitarReservaForm form, BindingResult result,
			SessionStatus status) {

		validator.validate(form, result);

		if (result.hasErrors()) {
			return new ModelAndView("editarSolicitarReserva");
		} else {
			
			MaterialBibliografico materialBibliografico = new MaterialBibliografico();
			materialBibliografico.setIdMaterialBibliografico(form.getIdMaterialBibliografico());
			
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(form.getIdUsuario());
			
			SolicitarReserva registro = new SolicitarReserva();
			registro.setIdSolicitarReserva(form.getIdSolicitarReserva());
			registro.setDataSolicitacao(form.getDataSolicitacao());
			registro.setObservacao(form.getObservacao());
			registro.setMaterialBibliografico(materialBibliografico);
			registro.setUsuario(usuario);

			try {
				service.insertOrUpdate(registro);
			} catch (SystemException e) {
				result.reject("error.insertOrUpdate");
				return new ModelAndView(new RedirectView("editar.form"));
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
	public ModelAndView apagar(Integer idSolicitarReserva) {
		ModelAndView view = new ModelAndView(new RedirectView("listar.form"));
		try {
			service.delete(idSolicitarReserva);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping("/listar.form")
	public String listar(ModelMap model) {
		return "listarSolicitarReserva";
	}

	@ModelAttribute("listaSolicitarReserva")
	public List<SolicitarReserva> populateListaSolicitarReserva() {
		return service.listar();
	}
}
