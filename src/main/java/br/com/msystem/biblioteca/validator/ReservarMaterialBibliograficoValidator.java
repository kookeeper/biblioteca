package br.com.msystem.biblioteca.validator;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.msystem.biblioteca.form.ReservarMaterialBibliograficoForm;

@Component
public class ReservarMaterialBibliograficoValidator implements Validator {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
	public boolean supports(Class<?> clazz) {
    	return ReservarMaterialBibliograficoForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ReservarMaterialBibliograficoForm form = (ReservarMaterialBibliograficoForm) obj;
		
		if (form.getMaterialBibliografico() == null) {
		  errors.reject("error.reservarMaterialBibliografico.null");
		}
		
		if (form.getDataInicioReserva() == null) {
			form.setDataInicioReserva(new Date());
		}
		
		if (form.getPeriodoReserva() == null) {
			form.setPeriodoReserva(5);
		}
		
		if (form.getPeriodoReserva().intValue() < 1) {
			form.setPeriodoReserva(5);
		}
		
	}

}
