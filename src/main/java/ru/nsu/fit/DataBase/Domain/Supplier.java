package ru.nsu.fit.DataBase.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer supplierId;

    private String organization;

    public Supplier(){};

    public Supplier(String organization){
        this.organization = organization;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer id) {
        this.supplierId = id;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
