package br.com.nutriCenter.exception;

public class LoginInvalidException extends Exception{

    public LoginInvalidException() {
        super("Info de Login Invalida");
    }
}
