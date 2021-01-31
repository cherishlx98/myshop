package com.lx.gateway.entity;

import java.io.Serializable;

public class ChangePasswordJson implements Serializable {

    private static final long serialVersionUID = 8999179065546557040L;
    private Long id;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordJson() {
    }

    public ChangePasswordJson(Long id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
