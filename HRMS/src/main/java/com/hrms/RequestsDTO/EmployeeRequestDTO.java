package com.hrms.RequestsDTO;

import com.hrms.enums.Gender;
import com.hrms.enums.Role;
import com.hrms.enums.Status;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeRequestDTO {

    private String eName;
    private String email;
    private String ePassword;        // request-only
    private String eDisgnation;
    private BigDecimal eSalary;
    private Date eJoinDate;

    private Long eManagerId;
    private Long eCreatedByHrId;

    private Role role;
    private Status estatus;
    private Gender eGender;

    private String ePhone;

    private Long departmentId;       // FK only
    private Long companyId;          // FK only

    // ---------- Getters & Setters ---------- //

    public String getEName() {
        return eName;
    }

    public void setEName(String eName) {
        this.eName = eName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEPassword() {
        return ePassword;
    }

    public void setEPassword(String ePassword) {
        this.ePassword = ePassword;
    }

    public String getEDisgnation() {
        return eDisgnation;
    }

    public void setEDisgnation(String eDisgnation) {
        this.eDisgnation = eDisgnation;
    }

    public BigDecimal getESalary() {
        return eSalary;
    }

    public void setESalary(BigDecimal eSalary) {
        this.eSalary = eSalary;
    }

    public Date getEJoinDate() {
        return eJoinDate;
    }

    public void setEJoinDate(Date eJoinDate) {
        this.eJoinDate = eJoinDate;
    }

    public Long getEManagerId() {
        return eManagerId;
    }

    public void setEManagerId(Long eManagerId) {
        this.eManagerId = eManagerId;
    }

    public Long getECreatedByHrId() {
        return eCreatedByHrId;
    }

    public void setECreatedByHrId(Long eCreatedByHrId) {
        this.eCreatedByHrId = eCreatedByHrId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getEstatus() {
        return estatus;
    }

    public void setEstatus(Status estatus) {
        this.estatus = estatus;
    }

    public Gender getEGender() {
        return eGender;
    }

    public void setEGender(Gender eGender) {
        this.eGender = eGender;
    }

    public String getEPhone() {
        return ePhone;
    }

    public void setEPhone(String ePhone) {
        this.ePhone = ePhone;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
