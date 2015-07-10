package br.com.msystem.biblioteca.validator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.msystem.biblioteca.form.EditarGrupoUsuarioPermissaoForm;
import br.com.msystem.biblioteca.service.GrupoUsuarioPermissaoService;

@Component
public class EditarGrupoUsuarioPermissaoValidator implements Validator {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private GrupoUsuarioPermissaoService service;

    @Override
	public boolean supports(Class<?> clazz) {
    	return EditarGrupoUsuarioPermissaoForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EditarGrupoUsuarioPermissaoForm form = (EditarGrupoUsuarioPermissaoForm) obj;
		
		if ((!form.getAlterar()) && (!form.getConsultar()) && (!form.getExcluir())) {
			errors.rejectValue("consultar", "error.grupoUsuarioPermissao.tipoPermissao.null");
		}
		
		Map<String, String> filtro = new HashMap<String, String>();
		filtro.put("id_permissao", form.getIdPermissao().toString());
		filtro.put("id_grupo_usuario", form.getFkGrupoUsuario().toString());
		if (service.buscar(filtro) != null) {
			errors.rejectValue("idPermissao", "error.grupoUsuarioPermissao.permissao.existente");
		}
	}

}
