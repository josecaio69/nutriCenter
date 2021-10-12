package br.com.nutriCenter.exception;

public class ObjectNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
		super("O objeto não foi encontrado");
	}
}
