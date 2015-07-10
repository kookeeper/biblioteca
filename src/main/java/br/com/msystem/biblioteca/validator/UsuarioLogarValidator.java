package br.com.msystem.biblioteca.validator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.msystem.biblioteca.form.UsuarioLogarForm;
import br.com.msystem.biblioteca.service.UsuarioService;
import br.com.msystem.biblioteca.vo.Usuario;

@Component
public class UsuarioLogarValidator implements Validator {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
	private UsuarioService usuarioService;

	@Override
	public boolean supports(Class<?> clazz) {
    	return UsuarioLogarForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UsuarioLogarForm form = (UsuarioLogarForm) obj;
		Map<String, String> filtro = new HashMap<String, String>();
		filtro.put("login", form.getLogin());
		Usuario usuario = usuarioService.buscar(filtro);
		
		if (usuario == null) {
			errors.rejectValue("login", "error.usuarioLogar.login.invalido");
		} else {
			if (!usuario.getSenha().equals(form.getSenha())) {
				errors.rejectValue("login", "error.usuarioLogar.login.invalido");
			}
		}
	}

}
