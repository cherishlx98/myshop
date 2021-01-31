package com.lx.gateway.entity;

import java.io.Serializable;

/**
 * 登陆json
 */
public class LoginJson implements Serializable {

    private static final long serialVersionUID = 9199945363978456221L;
    private String username;
    private String password;

    public LoginJson() {
    }

    public LoginJson(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
