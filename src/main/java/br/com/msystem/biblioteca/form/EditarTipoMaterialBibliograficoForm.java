package br.com.msystem.biblioteca.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EditarTipoMaterialBibliograficoForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private Integer idTipoMaterialBibliografico;

    private String descricaoTipoMaterialBibliografico;

	public Integer getIdTipoMaterialBibliografico() {
		return idTipoMaterialBibliografico;
	}

	public void setIdTipoMaterialBibliografico(Integer idTipoMaterialBibliografico) {
		this.idTipoMaterialBibliografico = idTipoMaterialBibliografico;
	}

	public String getDescricaoTipoMaterialBibliografico() {
		return descricaoTipoMaterialBibliografico;
	}

	public void setDescricaoTipoMaterialBibliografico(
			String descricaoTipoMaterialBibliografico) {
		this.descricaoTipoMaterialBibliografico = descricaoTipoMaterialBibliografico;
	}
    
}