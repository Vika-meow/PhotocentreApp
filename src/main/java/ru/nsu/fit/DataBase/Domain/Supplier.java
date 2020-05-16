package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer supplierId;

    private String organization;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "supplierId")
    private List<Delivery> deliveries;

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
