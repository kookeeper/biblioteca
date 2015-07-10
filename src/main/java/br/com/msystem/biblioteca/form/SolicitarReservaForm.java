package br.com.msystem.biblioteca.form;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SolicitarReservaForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	private Integer idSolicitarReserva;
    private Integer idUsuario;
    private Integer idMaterialBibliografico;
    private Date dataSolicitacao;
    private String observacao;
	public Integer getIdSolicitarReserva() {
		return idSolicitarReserva;
	}
	public void setIdSolicitarReserva(Integer idSolicitarReserva) {
		this.idSolicitarReserva = idSolicitarReserva;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdMaterialBibliografico() {
		return idMaterialBibliografico;
	}
	public void setIdMaterialBibliografico(Integer idMaterialBibliografico) {
		this.idMaterialBibliografico = idMaterialBibliografico;
	}
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
