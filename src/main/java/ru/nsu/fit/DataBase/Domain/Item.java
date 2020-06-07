package ru.nsu.fit.DataBase.Domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int itemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="checkId")
    private CheckEntity check;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="orderId")
    private OrderPrice orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="goodsId")
    private GoodsPrice goodsId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="serviceId")
    private ServicePrice serviceId;

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
            return orderId.getOrderType() + " " + orderId.getPaperType() +" " +  orderId.getFormat();
        }

        if(serviceId != null){
            return serviceId.getServiceType();
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

    //fororder
    public String getOrderType(){
        return orderId != null
                ? orderId.getOrderType() : "none";
    }

    public String getOrderPaperType(){
        return orderId != null
                ? orderId.getPaperType() : "none";
    }

    public String getOrderFormat(){
        return orderId != null
                ? orderId.getFormat() : "none";
    }

    public Integer getOrderPrice(){
        return orderId != null
                ? orderId.getPrice()*count : 0;
    }

    public String getCustomerName(){
        return check != null
                ? check.getCustomerName() : "null";
    }

    public String getAdress(){
        return check != null
                ? check.getOrganizationName() : "null";
    }

    public Date getDate(){
        return check != null
                ? check.getDate() : new Date(System.currentTimeMillis());
    }

    public String getNameOfGoods(){
        return goodsId.getNameOfGoods();
    }

    public String getCompany(){
        return  goodsId.getCompany();
    }

    public String getGoodsModel(){
        return goodsId.getGoodsModel();
    }

    public int getBuyPrice(){
        return goodsId.getBuyPrice();
    }

    public int getSellPrice(){
        return goodsId.getSellPrice();
    }
}
