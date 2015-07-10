package br.com.msystem.biblioteca.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.form.EditarGrupoUsuarioForm;
import br.com.msystem.biblioteca.form.EditarGrupoUsuarioPermissaoForm;
import br.com.msystem.biblioteca.service.GrupoUsuarioService;
import br.com.msystem.biblioteca.service.PermissaoService;
import br.com.msystem.biblioteca.validator.EditarGrupoUsuarioValidator;
import br.com.msystem.biblioteca.vo.GrupoUsuario;

@Controller
public class GrupoUsuarioController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private GrupoUsuarioService service;
	
	@Autowired
	private EditarGrupoUsuarioValidator validator;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@RequestMapping("/apagar.form")
	public ModelAndView apagar(Integer idGrupoUsuario) {
		ModelAndView view = new ModelAndView(new RedirectView("listar.form"));
		try {
			service.delete(idGrupoUsuario);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

  @RequestMapping(value = "/editar.form", method = RequestMethod.GET)
  public String get(Integer idGrupoUsuario, ModelMap model) {
    EditarGrupoUsuarioForm form = new EditarGrupoUsuarioForm();
    EditarGrupoUsuarioPermissaoForm permissaoForm = new EditarGrupoUsuarioPermissaoForm();
    
    if (idGrupoUsuario > 0) {
      GrupoUsuario registro = service.buscarPorId(idGrupoUsuario);
      model.addAttribute("idGrupoUsuario", idGrupoUsuario);
      model.addAttribute("listaGrupoUsuarioPermissao", registro.getGrupoUsuarioPermissaos());
      form.setDescricao(registro.getDescricaoGrupoUsuario());
      permissaoForm.setFkGrupoUsuario(registro.getIdGrupoUsuario());
    }
    
    model.addAttribute("listaPermissao", permissaoService.listar());
    model.addAttribute("editarForm", form);
    model.addAttribute("editarPermissaoForm", permissaoForm);
    
    return "editarGrupoUsuario";
  }

	@RequestMapping(value = "/editar.form", method = RequestMethod.POST)
	public ModelAndView post(EditarGrupoUsuarioForm form, BindingResult result, SessionStatus status) {
		
		validator.validate(form, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("editarGrupoUsuario");
		} else {
			GrupoUsuario grupoUsuario = new GrupoUsuario();
			if (form.getIdGrupoUsuario() > 0) {
				grupoUsuario = service.buscarPorId(form.getIdGrupoUsuario());
			}
			grupoUsuario.setDescricaoGrupoUsuario(form.getDescricao());
		
			service.insertOrUpdate(grupoUsuario);
		
			return new ModelAndView(new RedirectView("listar.form"));
		}
	}

	@RequestMapping("/listar.form")
	public String listar(ModelMap model) {
		return "listarGrupoUsuario";
	}

	@ModelAttribute("listaGrupoUsuario")
	public List<GrupoUsuario> populateListaGrupoUsuario() {
		return service.listar();
	}
}
