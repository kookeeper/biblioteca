package br.com.msystem.biblioteca.validator;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.msystem.biblioteca.form.EmprestarMaterialBibliograficoForm;

@Component
public class EmprestarMaterialBibliograficoValidator implements Validator {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
	public boolean supports(Class<?> clazz) {
    	return EmprestarMaterialBibliograficoForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EmprestarMaterialBibliograficoForm form = (EmprestarMaterialBibliograficoForm) obj;
		
		if (form.getDataDevolucaoPrevista() == null) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(new Date());
      cal.add(Calendar.DAY_OF_MONTH, 5);
			form.setDataDevolucaoPrevista(cal.getTime());
		}
		
		if (form.getDataEmprestimo() == null) {
		  form.setDataEmprestimo(new Date());
		}

	}

}
