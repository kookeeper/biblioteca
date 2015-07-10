package br.com.msystem.biblioteca.exception;


public class SystemException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -562866293167829312L;

	public SystemException(String mensagem, Throwable e) {
		super(mensagem, e);
	}

}
