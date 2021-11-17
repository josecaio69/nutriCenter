/**
 * 
 */
package br.com.nutriCenter.exception;

/**
 * @author José Caio
 *
 */
public class InvalidNutritionalAssessmentException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public InvalidNutritionalAssessmentException() {
		super("Dados de avalicao invalidos");
	}
}
