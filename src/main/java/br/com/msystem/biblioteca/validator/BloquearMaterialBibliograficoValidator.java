package br.com.msystem.biblioteca.validator;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.msystem.biblioteca.form.BloquearMaterialBibliograficoForm;

@Component
public class BloquearMaterialBibliograficoValidator implements Validator {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
	public boolean supports(Class<?> clazz) {
    	return BloquearMaterialBibliograficoForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		BloquearMaterialBibliograficoForm form = (BloquearMaterialBibliograficoForm) obj;
		
		if (form.getDataBloqueio() == null) {
			form.setDataBloqueio(new Date());
		}
	}

}
