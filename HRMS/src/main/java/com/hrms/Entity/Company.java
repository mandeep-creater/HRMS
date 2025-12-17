package com.hrms.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.sql.Struct;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "company")
public class Company extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cId;

    private String  companyCode;

    private String companyName;

    private String companyAddress;

    private String companyPhone;

    private  String companyEmail;

    private  boolean is_active = true ;



    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Company() {
    }

    public Company(String companyCode, String companyName, String companyAddress, String companyPhone, String companyEmail, boolean is_active, List<Employee> employees) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhone = companyPhone;
        this.companyEmail = companyEmail;
        this.is_active = is_active;
       /* this.created_at = created_at;
        this.updated_at = updated_at;*/
        this.employees = employees;
    }


    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }



    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

//    public LocalDateTime getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(LocalDateTime created_at) {
//        this.created_at = created_at;
//    }
//
//    public LocalDateTime getUpdated_at() {
//        return updated_at;
//    }
//
//    public void setUpdated_at(LocalDateTime updated_at) {
//        this.updated_at = updated_at;
//    }
}

