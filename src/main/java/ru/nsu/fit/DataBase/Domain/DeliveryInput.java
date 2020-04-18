package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;
//also need some Changes
@Entity
public class DeliveryInput {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int deliveryInputId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="deliveryId")
    Delivery delivery;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="goodsId")
    GoodsPrice goodsPrice;

    int count;

    public DeliveryInput(Delivery delivery, GoodsPrice goodsPrice, int count) {
        this.delivery = delivery;
        this.goodsPrice = goodsPrice;
        this.count = count;
    }

    public String getGoodsModel(){
        return goodsPrice != null
                ? goodsPrice.getGoodsModel() : "none";
    }

    public String getGoodsCompany(){
        return goodsPrice != null
                ? goodsPrice.getCompany() : "none";
    }

    public String getNameOfGoods(){
        return goodsPrice != null
                ? goodsPrice.getNameOfGoods() : "none";
    }

    public int getDeliveryInputId() {
        return deliveryInputId;
    }

    public void setDeliveryInputId(int deliveryInputId) {
        this.deliveryInputId = deliveryInputId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DeliveryInput(){}

    public Delivery getDelivery() {
        return delivery;
    }

    public int getDeliveryId() {
        return delivery.getDeliveryId();
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public GoodsPrice getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(GoodsPrice goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
