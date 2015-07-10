package br.com.msystem.biblioteca.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EditarGrupoUsuarioForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private Integer idGrupoUsuario;

    private String descricao;

	public Integer getIdGrupoUsuario() {
		return idGrupoUsuario;
	}

	public void setIdGrupoUsuario(Integer idGrupoUsuario) {
		this.idGrupoUsuario = idGrupoUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
