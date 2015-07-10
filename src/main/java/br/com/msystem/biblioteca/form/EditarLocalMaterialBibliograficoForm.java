package br.com.msystem.biblioteca.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EditarLocalMaterialBibliograficoForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private Integer idLocalMaterialBibliografico;

    private String descricaoLocalMaterialBibliografico;

	public Integer getIdLocalMaterialBibliografico() {
		return idLocalMaterialBibliografico;
	}

	public void setIdLocalMaterialBibliografico(Integer idLocalMaterialBibliografico) {
		this.idLocalMaterialBibliografico = idLocalMaterialBibliografico;
	}

	public String getDescricaoLocalMaterialBibliografico() {
		return descricaoLocalMaterialBibliografico;
	}

	public void setDescricaoLocalMaterialBibliografico(
			String descricaoLocalMaterialBibliografico) {
		this.descricaoLocalMaterialBibliografico = descricaoLocalMaterialBibliografico;
	}
    
}