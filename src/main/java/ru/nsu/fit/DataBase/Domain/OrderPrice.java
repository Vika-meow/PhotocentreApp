package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderPrice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int orderId;

    private String orderType;
    private String paperType;
    private String format;
    private int price;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "orderId")
    private List<Item> items;

    public OrderPrice() {
    }

    public OrderPrice(String orderType, int price){
        this.orderType = orderType;
        this.price = price;
    }

    public OrderPrice(String orderType, String paperType,
                      String format, int price){
        this.orderType = orderType;
        this.paperType = paperType;
        this.format = format;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int id) {
        this.orderId = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPaperType() {
        return paperType != null
                ? paperType : "none";
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getFormat() {
        return format != null
                ? format : "none";
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
