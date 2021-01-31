package com.lx.order.entity;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = 4235443920334909975L;

    private Long id;
    private Long goodId;
    private String payId;
    private Long userId;
    private Long createdTime;
    private String contact;//收货人
    private String phone;//收货人电话
    private String address;

    public Order() {
    }

    public Order(Long goodId, String payId, Long userId, Long createdTime, String contact, String address, String phone) {
        this.goodId = goodId;
        this.payId = payId;
        this.userId = userId;
        this.createdTime = createdTime;
        this.contact = contact;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", payId='" + payId + '\'' +
                ", userId=" + userId +
                ", createdTime=" + createdTime +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
