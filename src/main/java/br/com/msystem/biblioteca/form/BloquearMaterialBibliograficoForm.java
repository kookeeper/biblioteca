package br.com.msystem.biblioteca.form;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.msystem.biblioteca.vo.MaterialBibliografico;

public class BloquearMaterialBibliograficoForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	private Integer idBloquearMaterialBibliografico;

	private Integer idUsuario;
    
	private MaterialBibliografico materialBibliografico;
    
	private Date dataBloqueio;
    
	private String motivoBloqueio;

    private Boolean statusBloqueio;

	public Integer getIdBloquearMaterialBibliografico() {
		return idBloquearMaterialBibliografico;
	}

	public void setIdBloquearMaterialBibliografico(
			Integer idBloquearMaterialBibliografico) {
		this.idBloquearMaterialBibliografico = idBloquearMaterialBibliografico;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public MaterialBibliografico getMaterialBibliografico() {
		return materialBibliografico;
	}

	public void setMaterialBibliografico(MaterialBibliografico materialBibliografico) {
		this.materialBibliografico = materialBibliografico;
	}

	public Date getDataBloqueio() {
		return dataBloqueio;
	}

	public void setDataBloqueio(Date dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	public String getMotivoBloqueio() {
		return motivoBloqueio;
	}

	public void setMotivoBloqueio(String motivoBloqueio) {
		this.motivoBloqueio = motivoBloqueio;
	}

	public Boolean getStatusBloqueio() {
		return statusBloqueio;
	}

	public void setStatusBloqueio(Boolean statusBloqueio) {
		this.statusBloqueio = statusBloqueio;
	}

}
