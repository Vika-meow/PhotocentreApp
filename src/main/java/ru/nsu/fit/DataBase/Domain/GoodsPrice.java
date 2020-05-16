package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class GoodsPrice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int goodsId;

    private String nameOfGoods;
    private String company;
    private String goodsModel;
    private int buyPrice;
    private int sellPrice;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "goodsId")
    private List<DeliveryInput> deliveryInputs;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "goodsId")
    private List<Item> items;

    public GoodsPrice() {
    }

    public GoodsPrice(String nameOfGoods, String company, String goodsModel, int buyPrice) {
        this.nameOfGoods = nameOfGoods;
        this.company = company;
        this.goodsModel = goodsModel;
        this.buyPrice = buyPrice;
    }

    public GoodsPrice(String nameOfGoods, String company, String goodsModel, int buyPrice, int sellPrice) {
        this.nameOfGoods = nameOfGoods;
        this.company = company;
        this.goodsModel = goodsModel;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer id) {
        this.goodsId = id;
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
