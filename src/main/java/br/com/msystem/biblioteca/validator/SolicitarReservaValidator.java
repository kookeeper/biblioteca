package br.com.msystem.biblioteca.validator;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.msystem.biblioteca.form.SolicitarReservaForm;

@Component
public class SolicitarReservaValidator implements Validator {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
	public boolean supports(Class<?> clazz) {
    	return SolicitarReservaForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		SolicitarReservaForm form = (SolicitarReservaForm) obj;
		
		if (form.getDataSolicitacao() == null) {
			form.setDataSolicitacao(new Date());
		}
	}

}
