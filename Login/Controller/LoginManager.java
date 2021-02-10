package edu.citytech.Login.Controller;

public final class LoginManager {
    private String user;
    private final static LoginManager INSTANCE = new LoginManager();

    private LoginManager(){}

    public static LoginManager getInstance() {
        return INSTANCE;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
