package com.xlb.test;

public class Contributor {
    private String login;
    private int    contributions;

    public Contributor(String login, int contributions) {

        this.login = login;
        this.contributions = contributions;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

}
