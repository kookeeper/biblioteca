package br.com.msystem.biblioteca.form;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EditarMaterialBibliograficoForm {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private Integer idMaterialBibliografico;
	private Integer idTipoMaterialBibliografico;
	private Integer idLocalMaterialBibliografico;
	private String autor;
	private String titulo;
	private String edicao;
	private Date dataAquisicao;
	private String editora;
	private String resenha;
	private String codigoMaterialBibliografico;

	public Integer getIdMaterialBibliografico() {
		return idMaterialBibliografico;
	}

	public void setIdMaterialBibliografico(Integer idMaterialBibliografico) {
		this.idMaterialBibliografico = idMaterialBibliografico;
	}

	public Integer getIdTipoMaterialBibliografico() {
		return idTipoMaterialBibliografico;
	}

	public void setIdTipoMaterialBibliografico(
			Integer idTipoMaterialBibliografico) {
		this.idTipoMaterialBibliografico = idTipoMaterialBibliografico;
	}

	public Integer getIdLocalMaterialBibliografico() {
		return idLocalMaterialBibliografico;
	}

	public void setIdLocalMaterialBibliografico(
			Integer idLocalMaterialBibliografico) {
		this.idLocalMaterialBibliografico = idLocalMaterialBibliografico;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getResenha() {
		return resenha;
	}

	public void setResenha(String resenha) {
		this.resenha = resenha;
	}

	public String getCodigoMaterialBibliografico() {
		return codigoMaterialBibliografico;
	}

	public void setCodigoMaterialBibliografico(String codigoMaterialBibliografico) {
		this.codigoMaterialBibliografico = codigoMaterialBibliografico;
	}

}
