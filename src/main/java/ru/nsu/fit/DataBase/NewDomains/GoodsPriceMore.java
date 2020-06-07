package ru.nsu.fit.DataBase.NewDomains;

import ru.nsu.fit.DataBase.Domain.DeliveryInput;
import ru.nsu.fit.DataBase.Domain.Item;

import javax.persistence.*;
import java.util.List;

public class GoodsPriceMore {

    private String nameOfGoods;
    private String company;
    private String goodsModel;
    private int buyPrice;
    private int sellPrice;
    private int count;

    public GoodsPriceMore(String nameOfGoods, String company, String goodsModel,
                          int buyPrice, int sellPrice, int count) {
        this.nameOfGoods = nameOfGoods;
        this.company = company;
        this.goodsModel = goodsModel;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNameOfGoods() {
        return nameOfGoods;
    }

    public void setNameOfGoods(String nameOfGoods) {
        this.nameOfGoods = nameOfGoods;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String model) {
        this.goodsModel = model;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }
}
