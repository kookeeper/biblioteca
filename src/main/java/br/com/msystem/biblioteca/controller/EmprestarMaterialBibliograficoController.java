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
import br.com.msystem.biblioteca.form.EmprestarMaterialBibliograficoForm;
import br.com.msystem.biblioteca.service.EmprestarMaterialBibliograficoService;
import br.com.msystem.biblioteca.service.MaterialBibliograficoService;
import br.com.msystem.biblioteca.validator.EmprestarMaterialBibliograficoValidator;
import br.com.msystem.biblioteca.vo.EmprestarMaterialBibliografico;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class EmprestarMaterialBibliograficoController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private EmprestarMaterialBibliograficoService service;

	@Autowired
	private MaterialBibliograficoService materialBibliograficoService;

	@Autowired
	private EmprestarMaterialBibliograficoValidator validator;

	@RequestMapping(value = "/editar.form", method = RequestMethod.GET)
	public String get(Integer idEmprestarMaterialBibliografico, ModelMap model, HttpServletRequest request) {
	  EmprestarMaterialBibliograficoForm form = new EmprestarMaterialBibliograficoForm();

		if (idEmprestarMaterialBibliografico > 0) {
		  EmprestarMaterialBibliografico registro = service.buscarPorId(idEmprestarMaterialBibliografico);
		  form.setDataDevolucaoEfetiva(registro.getDataDevolucaoEfetiva());
		  form.setDataDevolucaoPrevista(registro.getDataDevolucaoPrevista());
		  form.setDataEmprestimo(registro.getDataEmprestimo());
		  form.setIdEmprestarMaterialBibliografico(registro.getIdEmprestarMaterialBibliografico());
		  form.setIdUsuario(registro.getUsuario().getIdUsuario());
		  form.setMaterialBibliografico(registro.getMaterialBibliografico());
		} else {
			Integer idUsuario = ((Usuario) request.getSession().getAttribute("usuario")).getIdUsuario();
			form.setIdUsuario(idUsuario);
		}

		model.addAttribute("emprestarMaterialBibliograficoForm", form);
		return "editarEmprestarMaterialBibliografico";
	}

	@RequestMapping(value = "/editar.form", method = RequestMethod.POST)
	public ModelAndView post(EmprestarMaterialBibliograficoForm form, BindingResult result,
			SessionStatus status) {

		validator.validate(form, result);

		if (result.hasErrors()) {
			return new ModelAndView("editarEmprestarMaterialBibliografico");
		} else {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(form.getIdUsuario());
			
			EmprestarMaterialBibliografico registro = new EmprestarMaterialBibliografico();
			registro.setDataDevolucaoEfetiva(form.getDataDevolucaoEfetiva());
			registro.setDataDevolucaoPrevista(form.getDataDevolucaoPrevista());
			registro.setDataEmprestimo(form.getDataEmprestimo());
			registro.setIdEmprestarMaterialBibliografico(form.getIdEmprestarMaterialBibliografico());
			registro.setMaterialBibliografico(form.getMaterialBibliografico());
			registro.setUsuario(usuario);

			try {
				service.insertOrUpdate(registro);
			} catch (SystemException e) {
				result.reject("error.insertOrUpdate");
				return new ModelAndView("editarEmprestarMaterialBibliografico");
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
	public ModelAndView apagar(Integer idEmprestarMaterialBibliografico) {
		ModelAndView view = new ModelAndView(new RedirectView("listar.form"));
		try {
			service.delete(idEmprestarMaterialBibliografico);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

  @RequestMapping("/listar.form")
  public String listar(ModelMap model) {
    return "listarEmprestarMaterialBibliografico";
  }

  @RequestMapping("/listarPendente.form")
  public String listarPendente(ModelMap model) {
    List<EmprestarMaterialBibliografico> listaEmprestimoPendente = service.listarEmprestimoPendente();
    model.addAttribute("listaEmprestimoPendente", listaEmprestimoPendente);
    return "listarEmprestimoMaterialBibliograficoPendente";
  }
  
  @RequestMapping(value = "devolver.form", method = RequestMethod.GET)
  public String getDevolver(Integer idEmprestarMaterialBibliografico, ModelMap model, HttpServletRequest request) {
    EmprestarMaterialBibliograficoForm form = new EmprestarMaterialBibliograficoForm();

    EmprestarMaterialBibliografico registro = service.buscarPorId(idEmprestarMaterialBibliografico);
    form.setDataDevolucaoEfetiva(new Date());
    form.setDataDevolucaoPrevista(registro.getDataDevolucaoPrevista());
    form.setDataEmprestimo(registro.getDataEmprestimo());
    form.setIdEmprestarMaterialBibliografico(registro.getIdEmprestarMaterialBibliografico());
    form.setIdUsuario(registro.getUsuario().getIdUsuario());
    form.setMaterialBibliografico(registro.getMaterialBibliografico());

    model.addAttribute("emprestarMaterialBibliograficoForm", form);
    return "devolverMaterialBibliografico";
  }

  @RequestMapping(value = "devolver.form", method = RequestMethod.POST)
  public ModelAndView postDevolver(EmprestarMaterialBibliograficoForm form, BindingResult result,
      SessionStatus status) {
    ModelAndView view = new ModelAndView(new RedirectView("listarPendente.form"));

    validator.validate(form, result);

    if (result.hasErrors()) {
      view = new ModelAndView("devolverMaterialBibliografico");
    } else {
      EmprestarMaterialBibliografico registro = service.buscarPorId(form.getIdEmprestarMaterialBibliografico());
      registro.setDataDevolucaoEfetiva(form.getDataDevolucaoEfetiva());
      try {
        service.insertOrUpdate(registro);
      } catch (SystemException e) {
        result.reject("error.insertOrUpdate");
        view = new ModelAndView("devolverMaterialBibliografico");
      }
    }
    return view;
  }

	@ModelAttribute("listaEmprestarMaterialBibliografico")
	public List<EmprestarMaterialBibliografico> populateListaEmprestarMaterialBibliografico() {
		return service.listar();
	}

}
