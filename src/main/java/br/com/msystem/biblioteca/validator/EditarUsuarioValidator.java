package br.com.msystem.biblioteca.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.msystem.biblioteca.form.EditarUsuarioForm;

@Component
public class EditarUsuarioValidator implements Validator {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
	public boolean supports(Class<?> clazz) {
    	return EditarUsuarioForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EditarUsuarioForm iu = (EditarUsuarioForm) obj;
		
		if (iu.getLogin().equals(iu.getSenha())) {
			errors.rejectValue("senha", "error.senha");
		}
	}

}
