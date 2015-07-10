package br.com.msystem.biblioteca.form;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.msystem.biblioteca.vo.MaterialBibliografico;

public class EmprestarMaterialBibliograficoForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	private Integer idEmprestarMaterialBibliografico;

	private Integer idUsuario;
    
	private MaterialBibliografico materialBibliografico;
	
	private Date dataDevolucaoEfetiva;
	
	private Date dataDevolucaoPrevista;
	
	private Date dataEmprestimo;
    
	public Integer getIdEmprestarMaterialBibliografico() {
		return idEmprestarMaterialBibliografico;
	}

	public void setIdEmprestarMaterialBibliografico(
			Integer idEmprestarMaterialBibliografico) {
		this.idEmprestarMaterialBibliografico = idEmprestarMaterialBibliografico;
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

  /**
   * @return the dataDevolucaoEfetiva
   */
  public Date getDataDevolucaoEfetiva() {
    return dataDevolucaoEfetiva;
  }

  /**
   * @param dataDevolucaoEfetiva the dataDevolucaoEfetiva to set
   */
  public void setDataDevolucaoEfetiva(Date dataDevolucaoEfetiva) {
    this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
  }

  /**
   * @return the dataDevolucaoPrevista
   */
  public Date getDataDevolucaoPrevista() {
    return dataDevolucaoPrevista;
  }

  /**
   * @param dataDevolucaoPrevista the dataDevolucaoPrevista to set
   */
  public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
    this.dataDevolucaoPrevista = dataDevolucaoPrevista;
  }

  /**
   * @return the dataEmprestimo
   */
  public Date getDataEmprestimo() {
    return dataEmprestimo;
  }

  /**
   * @param dataEmprestimo the dataEmprestimo to set
   */
  public void setDataEmprestimo(Date dataEmprestimo) {
    this.dataEmprestimo = dataEmprestimo;
  }

}
