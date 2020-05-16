package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Organization {

    @Id
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branchOfficeAdress")
    private Organization branchOfficeAdress;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "branchOfficeAdress")
    private List<Organization> kiosks;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "address")
    private List<CheckEntity> checkEntities;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "address")
    private List<Delivery> deliveries;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "address")
    private List<Worker> workers;

    public Organization(){

    }


    public Organization(String address){
        this.address = address;
    }

    public Organization(String address, Organization branchOfficeAdress) {
        this.address = address;
        this.branchOfficeAdress = branchOfficeAdress;
    }

    public Organization getBranchOfficeAdress() {
        return branchOfficeAdress;
    }

    public String getBranchOfficeAdressString() {
        if(branchOfficeAdress == null)
            return "none";
        return branchOfficeAdress.address;
    }

    public void setBranchOfficeAdress(Organization branchOfficeAdress) {
        this.branchOfficeAdress = branchOfficeAdress;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
