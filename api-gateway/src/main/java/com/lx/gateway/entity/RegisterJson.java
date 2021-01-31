package com.lx.gateway.entity;

import java.io.Serializable;

public class RegisterJson implements Serializable{
    private static final long serialVersionUID = -8403844927736131568L;
    private String openId;
    private String username;
    private String password;

    public RegisterJson() {
    }

    public RegisterJson(String openId, String username, String password) {
        this.openId = openId;
        this.username = username;
        this.password = password;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
