package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;

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



    public DeliveryInput(){}

    public DeliveryInput(Delivery delivery, GoodsPrice goodsPrice) {
        this.delivery = delivery;
        this.goodsPrice = goodsPrice;
    }

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
