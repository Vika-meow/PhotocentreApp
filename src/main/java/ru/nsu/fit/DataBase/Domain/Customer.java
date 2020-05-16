package ru.nsu.fit.DataBase.Domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    private String name;
    private boolean discountCard;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "name")
    private List<CheckEntity> checkEntities;

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

    public List<CheckEntity> getCheckEntities() {
        return checkEntities;
    }

    public void setCheckEntities(List<CheckEntity> checkEntities) {
        this.checkEntities = checkEntities;
    }
}
