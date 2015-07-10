package br.com.msystem.biblioteca.form;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.msystem.biblioteca.vo.MaterialBibliografico;

public class ReservarMaterialBibliograficoForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	private Integer idReservarMaterialBibliografico;

	private Integer idUsuario;
    
	private MaterialBibliografico materialBibliografico;
    
	private Date dataInicioReserva;
	
	private String observacao;
	
	private Integer periodoReserva;
	
	private Integer idSolicitarReserva;
	
	private Boolean statusReserva;

	public Integer getIdReservarMaterialBibliografico() {
		return idReservarMaterialBibliografico;
	}

	public void setIdReservarMaterialBibliografico(
			Integer idReservarMaterialBibliografico) {
		this.idReservarMaterialBibliografico = idReservarMaterialBibliografico;
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

	public Date getDataInicioReserva() {
		return dataInicioReserva;
	}

	public void setDataInicioReserva(Date dataInicioReserva) {
		this.dataInicioReserva = dataInicioReserva;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getPeriodoReserva() {
		return periodoReserva;
	}

	public void setPeriodoReserva(Integer periodoReserva) {
		this.periodoReserva = periodoReserva;
	}

	public Integer getIdSolicitarReserva() {
		return idSolicitarReserva;
	}

	public void setIdSolicitarReserva(Integer idSolicitarReserva) {
		this.idSolicitarReserva = idSolicitarReserva;
	}

	public Boolean getStatusReserva() {
		return statusReserva;
	}

	public void setStatusReserva(Boolean statusReserva) {
		this.statusReserva = statusReserva;
	}
    
}
