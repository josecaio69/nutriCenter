package br.com.nutriCenter.exception;

public class PasswordInvalidException extends Exception{
    public PasswordInvalidException() {
        super("Senha Invalida");
    }
}
