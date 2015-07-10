package br.com.msystem.biblioteca.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EditarGrupoUsuarioPermissaoForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private Integer fkGrupoUsuario;
    
    private Integer idPermissao;
    
    private Boolean consultar;
    
    private Boolean alterar;
    
    private Boolean excluir;

	public Integer getFkGrupoUsuario() {
		return fkGrupoUsuario;
	}

	public void setFkGrupoUsuario(Integer fkGrupoUsuario) {
		this.fkGrupoUsuario = fkGrupoUsuario;
	}

	public Integer getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(Integer idPermissao) {
		this.idPermissao = idPermissao;
	}

	public Boolean getConsultar() {
		return consultar;
	}

	public void setConsultar(Boolean consultar) {
		this.consultar = consultar;
	}

	public Boolean getAlterar() {
		return alterar;
	}

	public void setAlterar(Boolean alterar) {
		this.alterar = alterar;
	}

	public Boolean getExcluir() {
		return excluir;
	}

	public void setExcluir(Boolean excluir) {
		this.excluir = excluir;
	}

}
