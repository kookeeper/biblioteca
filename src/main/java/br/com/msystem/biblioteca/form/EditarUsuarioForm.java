package br.com.msystem.biblioteca.form;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EditarUsuarioForm {

	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private Integer idUsuario;
    private Integer grupoUsuario;
    private String login;
    private String senha;
    private Date dataCadastro;
    private Boolean bloqueado;
    private String motivoBloqueio;
    private String email;
    private String nomeUsuario;
    private String nomeUsuarioChines;
    private String endereco;
    private String telefone;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(Integer grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getMotivoBloqueio() {
		return motivoBloqueio;
	}

	public void setMotivoBloqueio(String motivoBloqueio) {
		this.motivoBloqueio = motivoBloqueio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeUsuarioChines() {
		return nomeUsuarioChines;
	}

	public void setNomeUsuarioChines(String nomeUsuarioChines) {
		this.nomeUsuarioChines = nomeUsuarioChines;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
