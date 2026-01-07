package com.hrms.ResponseDTO;

import com.hrms.enums.Gender;
import com.hrms.enums.Role;
import com.hrms.enums.Status;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeResponseDTO {

    private Long eId;

    private String eName;

    private  String ePassword;
    private String email;
    private String eDisgnation;
    private BigDecimal eSalary;
    private Date eJoinDate;

    private Long eManagerId;
    private Long eCreatedByHrId;

    private String ePhone;
    private Gender eGender;
    private Status estatus;
    private Role role;

    private Long departmentId;
    private Long companyId;
    private String companyCode;

    private  Long companyLocationId;

    private  String companyLocationCode;

    // ---------- Getters & Setters ---------- //

    public Long getEId() {
        return eId;
    }

    public void setEId(Long eId) {
        this.eId = eId;
    }

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

    public String getEPhone() {
        return ePhone;
    }

    public void setEPhone(String ePhone) {
        this.ePhone = ePhone;
    }

    public Gender getEGender() {
        return eGender;
    }

    public void setEGender(Gender eGender) {
        this.eGender = eGender;
    }

    public Status getEstatus() {
        return estatus;
    }

    public void setEstatus(Status estatus) {
        this.estatus = estatus;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEPassword() {
        return ePassword;
    }

    public void setEPassword(String ePassword) {
        this.ePassword = ePassword;
    }

    public Long getCompanyLocationId() {
        return companyLocationId;
    }

    public void setCompanyLocationId(Long companyLocationId) {
        this.companyLocationId = companyLocationId;
    }

    public String getCompanyLocationCode() {
        return companyLocationCode;
    }

    public void setCompanyLocationCode(String companyLocationCode) {
        this.companyLocationCode = companyLocationCode;
    }
}
