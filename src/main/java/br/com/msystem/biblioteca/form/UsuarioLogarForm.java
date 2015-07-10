package br.com.msystem.biblioteca.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UsuarioLogarForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private String login;
    
    private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
