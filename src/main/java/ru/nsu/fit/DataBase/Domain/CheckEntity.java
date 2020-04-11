package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class CheckEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    public CheckEntity(Date date, Organization organization, Customer customer) {
        this.date = date;
        this.organization = organization;
        this.customer = customer;
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
