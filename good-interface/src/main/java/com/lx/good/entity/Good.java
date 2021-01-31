package com.lx.good.entity;

import java.io.Serializable;

public class Good implements Serializable {

    private static final long serialVersionUID = -3424535993713060945L;
    private Long id;
    private Integer price;
    private Integer shipCost;
    private String title;
    private String detail;
    private String pic;

    public Good() {
    }

    public Good(Long id, Integer price, Integer shipCost, String title, String detail, String pic) {
        this.id = id;
        this.price = price;
        this.shipCost = shipCost;
        this.title = title;
        this.detail = detail;
        this.pic = pic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getShipCost() {
        return shipCost;
    }

    public void setShipCost(Integer shipCost) {
        this.shipCost = shipCost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", price=" + price +
                ", shipCost=" + shipCost +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
