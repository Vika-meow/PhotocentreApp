package ru.nsu.fit.DataBase.Domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Worker {

    private String profile;
    @Id
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    private Organization address;

    public Worker() {
    }

    public Worker(String name, String profile, Organization address){
        this.name = name;
        this.profile = profile;
        this.address = address;
    }

    public String getOrganizationName(){ return address != null
            ? address.getAddress() : "none"; }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getAddress() {
        return address;
    }

    public void setAddress(Organization address) {
        this.address = address;
    }
}
