package com.lx.gateway.entity;

import java.io.Serializable;

public class OrderCreateJson implements Serializable {

    private static final long serialVersionUID = -4822664575490093707L;
    private Long goodId;
    private String contact;
    private String phone;
    private String address;

    public OrderCreateJson() {
    }

    public OrderCreateJson(Long goodId, String contact, String phone, String address) {
        this.goodId = goodId;
        this.contact = contact;
        this.phone = phone;
        this.address = address;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
