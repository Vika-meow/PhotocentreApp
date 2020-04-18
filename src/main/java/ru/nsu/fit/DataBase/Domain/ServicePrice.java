package ru.nsu.fit.DataBase.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServicePrice {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int serviceId;

    private String serviceType;
    private int price;

    public ServicePrice(){

    }

    public ServicePrice(String serviceType, int price) {
        this.serviceType = serviceType;
        this.price = price;
    }


    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int id) {
        this.serviceId = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
