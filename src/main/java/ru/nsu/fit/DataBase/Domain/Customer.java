package ru.nsu.fit.DataBase.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    private String name;
    private boolean discountCard;

    public Customer() {
    }

    public Customer(String name){
        this.name = name;
    }


    public Customer(String name, boolean discountCard){
        this.name = name;
        this.discountCard = discountCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(boolean discountCard) {
        this.discountCard = discountCard;
    }
}
