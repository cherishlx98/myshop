package com.lx.auth.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 8172201377888636039L;
    private Long id;
    private String openId;
    private String username;
    private String password;

    public User() {
    }

    public User(String openId, String username, String password) {
        this.openId = openId;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "com.lx.entity.User{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
