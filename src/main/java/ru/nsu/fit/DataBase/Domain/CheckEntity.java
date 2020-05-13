package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class CheckEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int checkId;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    private Organization organization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name")
    private Customer customer;

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
}
