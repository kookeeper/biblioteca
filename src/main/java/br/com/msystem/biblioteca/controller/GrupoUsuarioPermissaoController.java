package br.com.msystem.biblioteca.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.form.EditarGrupoUsuarioPermissaoForm;
import br.com.msystem.biblioteca.service.GrupoUsuarioPermissaoService;
import br.com.msystem.biblioteca.validator.EditarGrupoUsuarioPermissaoValidator;
import br.com.msystem.biblioteca.vo.GrupoUsuario;
import br.com.msystem.biblioteca.vo.GrupoUsuarioPermissao;
import br.com.msystem.biblioteca.vo.Permissao;

@Controller
public class GrupoUsuarioPermissaoController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private GrupoUsuarioPermissaoService service;

	@Autowired
	private EditarGrupoUsuarioPermissaoValidator validator;

	@RequestMapping("/apagar.form")
	public ModelAndView apagar(Integer idGrupoUsuario,
			Integer idGrupoUsuarioPermissao) {
		ModelAndView view = new ModelAndView(new RedirectView(
				"../grupousuario/editar.form"));
		view.getModel().put("idGrupoUsuario", idGrupoUsuario);
		try {
			service.delete(idGrupoUsuarioPermissao);
		} catch (SystemException e) {
			view.getModel().put("error", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping("/editar.form")
	public ModelAndView editar(EditarGrupoUsuarioPermissaoForm form,
			BindingResult result, SessionStatus status) {

		ModelAndView view = new ModelAndView(new RedirectView(
				"../grupousuario/editar.form"));

		view.getModel().put("idGrupoUsuario", form.getFkGrupoUsuario());

		validator.validate(form, result);
		
		if (!result.hasErrors()) {
			GrupoUsuarioPermissao registro = new GrupoUsuarioPermissao();

			GrupoUsuario grupoUsuario = new GrupoUsuario();
			grupoUsuario.setIdGrupoUsuario(form.getFkGrupoUsuario());
			registro.setGrupoUsuario(grupoUsuario);

			Permissao permissao = new Permissao();
			permissao.setIdPermissao(form.getIdPermissao());
			registro.setPermissao(permissao);

			Set<String> tipoPermissao = new HashSet<String>();
			if ((form.getConsultar() != null) && form.getConsultar()) {
				tipoPermissao.add("consultar");
			}
			if ((form.getAlterar() != null) && form.getAlterar()) {
				tipoPermissao.add("alterar");
			}
			if ((form.getExcluir() != null) && form.getExcluir()) {
				tipoPermissao.add("excluir");
			}
			registro.setTipoPermissao(tipoPermissao);
			service.insertOrUpdate(registro);
		}
		return view;
	}
}
