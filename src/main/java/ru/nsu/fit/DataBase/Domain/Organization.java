package ru.nsu.fit.DataBase.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Organization {

    @Id
    private String address;

    public Organization(){

    }

    public Organization(String address){
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
