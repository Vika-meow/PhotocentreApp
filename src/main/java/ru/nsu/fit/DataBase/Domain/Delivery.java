package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int deliveryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    private Organization address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplierId")
    private Supplier supplierId;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "deliveryId")
    private List<DeliveryInput> deliveryInputs;

    private Date date;

    public Delivery(){
    }

    public Delivery(Organization address, Supplier supplierId, Date date) {
        this.address = address;
        this.supplierId = supplierId;
        this.date = date;
    }
    

    public String getOrganizationName(){ return address != null
            ? address.getAddress() : "none";
    }

    public String getSupplierName() { return supplierId != null
            ? supplierId.getOrganization() : "none";
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int id) {
        this.deliveryId = id;
    }

    public Organization getAddress() {
        return address;
    }

    public void setAddress(Organization address) {
        this.address = address;
    }

    public Supplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Supplier supplierId) {
        this.supplierId = supplierId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
