package br.com.msystem.biblioteca.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import br.com.msystem.biblioteca.form.ReservarMaterialBibliograficoForm;
import br.com.msystem.biblioteca.service.EmprestarMaterialBibliograficoService;
import br.com.msystem.biblioteca.service.MaterialBibliograficoService;
import br.com.msystem.biblioteca.service.ReservarMaterialBibliograficoService;
import br.com.msystem.biblioteca.validator.ReservarMaterialBibliograficoValidator;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;
import br.com.msystem.biblioteca.vo.ReservarMaterialBibliografico;
import br.com.msystem.biblioteca.vo.SolicitarReserva;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class ReservarMaterialBibliograficoController {

	protected final Log logger = LogFactory.getLog(getClass());

  @Autowired
  private ReservarMaterialBibliograficoService service;

  @Autowired
  private EmprestarMaterialBibliograficoService emprestarMaterialBibliograficoService;

  @Autowired
  private MaterialBibliograficoService materialBibliograficoService;

	@Autowired
	private ReservarMaterialBibliograficoValidator validator;

  @RequestMapping(value = "/reservar.form", method = RequestMethod.GET)
  public String getReservar(ModelMap model, HttpServletRequest request) {
    ReservarMaterialBibliograficoForm form = new ReservarMaterialBibliograficoForm();

    Integer idUsuario = ((Usuario) request.getSession().getAttribute("usuario")).getIdUsuario();
    form.setIdUsuario(idUsuario);
    form.setDataInicioReserva(new Date());
    form.setPeriodoReserva(5);
    form.setStatusReserva(true);

    model.addAttribute("reservarMaterialBibliograficoForm", form);
    return "reservarMaterialBibliografico";
  }

  @RequestMapping(value = "/reservar.form", method = RequestMethod.POST)
  public ModelAndView postReservar(ReservarMaterialBibliograficoForm form, BindingResult result,
      SessionStatus status, HttpServletRequest request) {
    validator.validate(form, result);

    if (result.hasErrors()) {
      return new ModelAndView("reservarMaterialBibliografico");
    } else {
      Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
      
      ReservarMaterialBibliografico registro = new ReservarMaterialBibliografico();
      registro.setIdReservarMaterialBibliografico(0);
      registro.setDataInicioReserva(form.getDataInicioReserva());
      registro.setMaterialBibliografico(form.getMaterialBibliografico());
      registro.setObservacao(form.getObservacao());
      registro.setPeriodoReserva(form.getPeriodoReserva());
      registro.setStatusReservaBoolean(true);
      registro.setUsuario(usuario);

      try {
        service.insertOrUpdate(registro);
      } catch (SystemException e) {
        result.reject("error.insertOrUpdate");
        return new ModelAndView("reservarMaterialBibliografico");
      }

      return new ModelAndView(new RedirectView("../main/main.form"));
    }
  }

  @RequestMapping(value = "/listarEfetivarReserva.form")
  public ModelAndView listaEfetivarReserva() {
    ModelAndView model = new ModelAndView("listarReservaMaterialBibliograficoPendente");
    model.getModel().put("listaReservaPendente", service.listarReservaPendente());
    return model;
  }

  @RequestMapping("/listar.form")
  public String listar(ModelMap model) {
    return "listarReservarMaterialBibliografico";
  }

	@RequestMapping(value = "/editar.form", method = RequestMethod.GET)
	public String get(Integer idReservarMaterialBibliografico, ModelMap model, HttpServletRequest request) {
		ReservarMaterialBibliograficoForm form = new ReservarMaterialBibliograficoForm();

		if (idReservarMaterialBibliografico > 0) {
			ReservarMaterialBibliografico registro = service.buscarPorId(idReservarMaterialBibliografico);
			form.setDataInicioReserva(registro.getDataInicioReserva());
			form.setIdReservarMaterialBibliografico(registro.getIdReservarMaterialBibliografico());
			if (registro.getSolicitarReserva() != null) {
				form.setIdSolicitarReserva(registro.getSolicitarReserva().getIdSolicitarReserva());
			}
			form.setIdUsuario(registro.getUsuario().getIdUsuario());
			form.setMaterialBibliografico(registro.getMaterialBibliografico());
			form.setObservacao(registro.getObservacao());
			form.setPeriodoReserva(registro.getPeriodoReserva());
			form.setStatusReserva(registro.getStatusReservaBoolean());
		} else {
			Integer idUsuario = ((Usuario) request.getSession().getAttribute("usuario")).getIdUsuario();
			form.setIdUsuario(idUsuario);
			form.setDataInicioReserva(new Date());
			form.setPeriodoReserva(5);
			form.setStatusReserva(true);
		}

		model.addAttribute("reservarMaterialBibliograficoForm", form);
		return "editarReservarMaterialBibliografico";
	}

	@RequestMapping(value = "/editar.form", method = RequestMethod.POST)
	public ModelAndView post(ReservarMaterialBibliograficoForm form, BindingResult result,
			SessionStatus status) {

		validator.validate(form, result);

		if (result.hasErrors()) {
			return new ModelAndView("editarReservarMaterialBibliografico");
		} else {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(form.getIdUsuario());
			
			SolicitarReserva solicitarReserva = new SolicitarReserva();
			solicitarReserva.setIdSolicitarReserva(form.getIdSolicitarReserva());
			
			ReservarMaterialBibliografico registro = new ReservarMaterialBibliografico();
			registro.setDataInicioReserva(form.getDataInicioReserva());
			registro.setIdReservarMaterialBibliografico(form.getIdReservarMaterialBibliografico());
			registro.setMaterialBibliografico(form.getMaterialBibliografico());
			registro.setObservacao(form.getObservacao());
			registro.setPeriodoReserva(form.getPeriodoReserva());
			registro.setStatusReservaBoolean(form.getStatusReserva());
			registro.setSolicitarReserva(solicitarReserva);
			registro.setUsuario(usuario);

			try {
				service.insertOrUpdate(registro);
			} catch (SystemException e) {
				result.reject("error.insertOrUpdate");
				return new ModelAndView("editarReservarMaterialBibliografico");
			}

			return new ModelAndView(new RedirectView("listar.form"));
		}
	}

	@ModelAttribute("listaMaterialBibliograficoDisponivel")
	public List<MaterialBibliografico> populateListaMaterialBibliograficoDisponivel() {
		return materialBibliograficoService.listarDisponivel();
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
	public ModelAndView apagar(Integer idReservarMaterialBibliografico) {
		ModelAndView view = new ModelAndView(new RedirectView("listar.form"));
		try {
			service.delete(idReservarMaterialBibliografico);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

	@ModelAttribute("listaReservarMaterialBibliografico")
	public List<ReservarMaterialBibliografico> populateListaReservarMaterialBibliografico() {
		return service.listar();
	}

	@RequestMapping("/converter.form")
	public ModelAndView converterReservaMaterialBibliografico(HttpServletRequest request, HttpServletResponse response) {

	  ModelAndView model = new ModelAndView(new RedirectView("listarEfetivarReserva.form"));

	  Integer idReservarMaterialBibliografico = Integer.valueOf(request.getParameter("idReservarMaterialBibliografico"));
	  ReservarMaterialBibliografico reserva = service.buscarPorId(idReservarMaterialBibliografico);
	  
	  if (!reserva.getStatusReservaBoolean()) {
	    model.getModel().put("error", "error.converterReserva.status");
	  } else {
      try {
        emprestarMaterialBibliograficoService.emprestar(reserva);
      } catch (SystemException e) {
        model.getModel().put("error", e.getMessage());
        logger.error(e);
      }
	  }
	  
	  return model;
	}

}
