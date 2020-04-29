package ru.nsu.fit.DataBase.Domain;

import javax.persistence.*;

@Entity
public class Organization {

    @Id
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branchOfficeAdress")
    private Organization branchOfficeAdress;

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
