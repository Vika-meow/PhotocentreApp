package ru.nsu.fit.DataBase.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderPrice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    public String orderType;
    public String paperType;
    public String format;
    public int price;

    public OrderPrice() {
    }

    public OrderPrice(String orderType, String paperType,
                      String format, int price){
        this.orderType = orderType;
        this.paperType = paperType;
        this.format = format;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
