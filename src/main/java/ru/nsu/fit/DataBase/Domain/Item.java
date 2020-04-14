package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int itemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="checkId")
    CheckEntity check;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="orderId")
    OrderPrice orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="goodsId")
    GoodsPrice goodsId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="serviceId")
    ServicePrice serviceId;

    int count;
    boolean urgency;

    public Item(){

    }

    public Item(CheckEntity check, ServicePrice serviceId, int count, boolean urgency) {
        this.check = check;
        this.serviceId = serviceId;
        this.count = count;
        this.urgency = urgency;
    }

    public Item(CheckEntity check, GoodsPrice goodsId, int count, boolean urgency) {
        this.check = check;
        this.goodsId = goodsId;
        this.count = count;
        this.urgency = urgency;
    }

    public Item(CheckEntity check, OrderPrice orderId, int count, boolean urgency) {
        this.check = check;
        this.orderId = orderId;
        this.count = count;
        this.urgency = urgency;
    }

    public Item(CheckEntity check, ServicePrice serviceId, int count) {
        this.check = check;
        this.serviceId = serviceId;
        this.count = count;
    }

    public Item(CheckEntity check, GoodsPrice goodsId, int count) {
        this.check = check;
        this.goodsId = goodsId;
        this.count = count;
    }
    public Item(CheckEntity check, OrderPrice orderId, int count) {
        this.check = check;
        this.orderId = orderId;
        this.count = count;
    }

    public String getItemInput(){
        if(orderId != null){
            return orderId.orderType + " " + orderId.paperType +" " +  orderId.format;
        }

        if(serviceId != null){
            return serviceId.serviceType;
        }

        if(goodsId != null){
            return goodsId.getNameOfGoods() + " " +  goodsId.getCompany() +" " +  goodsId.getGoodsModel();
        }

        return "none";
    }

    public CheckEntity getCheck() {
        return check;
    }

    public void setCheck(CheckEntity check) {
        this.check = check;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public OrderPrice getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderPrice orderId) {
        this.orderId = orderId;
    }

    public GoodsPrice getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(GoodsPrice goodsId) {
        this.goodsId = goodsId;
    }

    public ServicePrice getServiceId() {
        return serviceId;
    }

    public void setServiceId(ServicePrice serviceId) {
        this.serviceId = serviceId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isUrgency() {
        return urgency;
    }

    public void setUrgency(boolean urgency) {
        this.urgency = urgency;
    }
}
