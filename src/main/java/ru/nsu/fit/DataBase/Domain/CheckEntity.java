package ru.nsu.fit.DataBase.Domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
//src/main/java/ru/nsu/fit/DataBase/application.properties
@Entity
public class CheckEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int checkId;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Organization organization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name")
    private Customer customer;
/*______________*/
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "checkId")
    List<Item> items;

    public CheckEntity() {
    }

    public CheckEntity(Organization organization, Customer customer, Date date) {
        this.date = date;
        this.organization = organization;
        this.customer = customer;
    }

    public String getOrganizationName(){ return organization != null
            ? organization.getAddress() : "none";
    }

    public String getCustomerName() { return customer != null
            ? customer.getName() : "none";
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
