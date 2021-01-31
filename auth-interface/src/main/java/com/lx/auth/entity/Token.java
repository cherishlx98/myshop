package com.lx.auth.entity;

import java.io.Serializable;

public class Token implements Serializable {

    private static final long serialVersionUID = 8037611427921317302L;

    private String token;
    private Long userId;

    public Token() {
    }

    public Token(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}
