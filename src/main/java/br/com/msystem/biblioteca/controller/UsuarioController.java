package br.com.msystem.biblioteca.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import br.com.msystem.biblioteca.form.EditarUsuarioForm;
import br.com.msystem.biblioteca.form.UsuarioLogarForm;
import br.com.msystem.biblioteca.service.GrupoUsuarioService;
import br.com.msystem.biblioteca.service.UsuarioService;
import br.com.msystem.biblioteca.validator.EditarUsuarioValidator;
import br.com.msystem.biblioteca.validator.UsuarioLogarValidator;
import br.com.msystem.biblioteca.vo.GrupoUsuario;
import br.com.msystem.biblioteca.vo.Usuario;

@Controller
public class UsuarioController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UsuarioService service;

	@Autowired
	private GrupoUsuarioService grupoUsuarioService;

	@Autowired
	private EditarUsuarioValidator validator;

	@Autowired
	private UsuarioLogarValidator logarValidator;

	@RequestMapping(value = "/editar.form", method = RequestMethod.GET)
	public String getEditarUsuario(Integer idUsuario, ModelMap model) {
		EditarUsuarioForm form = new EditarUsuarioForm();

		if (idUsuario > 0) {
			Usuario registro = service.buscarPorId(idUsuario);
			form.setIdUsuario(registro.getIdUsuario());
			form.setBloqueado(registro.isBloqueado());
			form.setDataCadastro(registro.getDataCadastro());
			form.setEmail(registro.getEmail());
			form.setNomeUsuario(registro.getNomeUsuario());
			form.setGrupoUsuario(registro.getGrupoUsuario().getIdGrupoUsuario());
			form.setLogin(registro.getLogin());
			form.setMotivoBloqueio(registro.getMotivoBloqueio());
			form.setSenha(registro.getSenha());
			form.setNomeUsuarioChines(registro.getNomeUsuarioChines());
			form.setEndereco(registro.getEndereco());
			form.setTelefone(registro.getTelefone());
		}

		model.addAttribute("editarUsuarioForm", form);
		return "editarUsuario";
	}

	@RequestMapping(value = "/editar.form", method = RequestMethod.POST)
	public ModelAndView postEditarUsuario(EditarUsuarioForm form, BindingResult result,
			SessionStatus status) {

		validator.validate(form, result);

		if (result.hasErrors()) {
			return new ModelAndView("editarUsuario");
		} else {
			Usuario registro = new Usuario();
			
			if (form.getIdUsuario() > 0) {
				registro = service.buscarPorId(form.getIdUsuario());
			}

			GrupoUsuario grupoUsuario = new GrupoUsuario();
			grupoUsuario.setIdGrupoUsuario(form.getGrupoUsuario());

			registro.setGrupoUsuario(grupoUsuario);
			registro.setLogin(form.getLogin());
			registro.setSenha(form.getSenha());
			registro.setDataCadastro(new Date());
			registro.setEmail(form.getEmail());
			registro.setNomeUsuario(form.getNomeUsuario());
			registro.setBloqueado(form.getBloqueado());
			registro.setMotivoBloqueio(form.getMotivoBloqueio());
			registro.setNomeUsuarioChines(form.getNomeUsuarioChines());
			registro.setEndereco(form.getEndereco());
			registro.setTelefone(form.getTelefone());

			try {
				service.insertOrUpdate(registro);
			} catch (SystemException e) {
				result.reject("error.insertOrUpdate");
			}

			return new ModelAndView(new RedirectView("listar.form"));
		}
	}

	@ModelAttribute("listaGrupoUsuario")
	public Map<Integer, String> populateListaGrupoUsuario() {

		Map<Integer, String> listaGrupoUsuario = new LinkedHashMap<Integer, String>();

		List<GrupoUsuario> lista = grupoUsuarioService.listar();

		Iterator<GrupoUsuario> it = lista.iterator();

		while (it.hasNext()) {
			GrupoUsuario grupoUsuario = it.next();
			listaGrupoUsuario.put(grupoUsuario.getIdGrupoUsuario(),
					grupoUsuario.getDescricaoGrupoUsuario());
		}
		return listaGrupoUsuario;
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
	public ModelAndView apagar(Integer idUsuario) {
		ModelAndView view = new ModelAndView(new RedirectView("listar.form"));
		try {
			service.delete(idUsuario);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping("/listar.form")
	public String listar(ModelMap model) {
		return "usuarioListar";
	}

	@ModelAttribute("listaUsuario")
	public List<Usuario> populateListaUsuario() {
		return service.listar();
	}

  @RequestMapping(value = "logar.form", method = RequestMethod.GET)
  public String getLogar(ModelMap model) {
    model.addAttribute("usuarioLogarForm", new UsuarioLogarForm());
    return "usuarioLogar";
  }

	@RequestMapping(value = "logar.form", method = RequestMethod.POST)
	public ModelAndView postLogar(UsuarioLogarForm form,
			BindingResult result, SessionStatus status, HttpServletRequest request) {
		
		logarValidator.validate(form, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("usuarioLogar");
		} else {
			request.getSession().setAttribute("usuario", service.buscar("login", form.getLogin()));
			return new ModelAndView(new RedirectView("../main/main.form"));
		}
	}
}