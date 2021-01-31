package com.lx.gateway.entity;

import java.io.Serializable;

public class UserLoginInfo implements Serializable {

    private static final long serialVersionUID = -67453857929530771L;

    private String token;
    private Long userId;
    private String username;
    private String openId;

    public UserLoginInfo() {
    }

    public UserLoginInfo(String token, Long userId, String username, String openId) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.openId = openId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
