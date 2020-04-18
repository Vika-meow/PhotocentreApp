package ru.nsu.fit.DataBase.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderPrice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int orderId;

    private String orderType;
    private String paperType;
    private String format;
    private int price;

    public OrderPrice() {
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
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getFormat() {
        return format;
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
