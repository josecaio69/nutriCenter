package br.com.nutriCenter.model;

public class Login {

    private final String userName;
    private final String password;
    private final int nivelDeAcesso;


    public Login(String userName, String password, int nivelDeAcesso) {
		this.userName = userName;
		this.password = password;
		this.nivelDeAcesso = nivelDeAcesso;
	}

    
	public int getNivelDeAcesso() {
		return nivelDeAcesso;
	}

	public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

