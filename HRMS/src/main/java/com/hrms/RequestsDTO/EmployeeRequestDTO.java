package com.hrms.RequestsDTO;

import java.math.BigDecimal;
import java.util.Date;

import com.hrms.enums.Gender;
import com.hrms.enums.Role;
import com.hrms.enums.Status;

public class EmployeeRequestDTO {

    private String companyCode;

    private String eName;

    private String eEmail;

    private String ePassword;        // ✅ only here

    private String eDisgnation;

    private BigDecimal eSalary;

    private Date eJoinDate;

    private Long eManagerId;

    private Long eCreatedByHrId;

    private Role role;

    private Long departmentId;       // ✅ ID only

    private String ePhone;

    private Status estatus;

    private Gender eGender;

    private Long companyId;          // ✅ ID only

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }

    public String getePassword() {
        return ePassword;
    }

    public void setePassword(String ePassword) {
        this.ePassword = ePassword;
    }

    public String geteDisgnation() {
        return eDisgnation;
    }

    public void seteDisgnation(String eDisgnation) {
        this.eDisgnation = eDisgnation;
    }

    public BigDecimal geteSalary() {
        return eSalary;
    }

    public void seteSalary(BigDecimal eSalary) {
        this.eSalary = eSalary;
    }

    public Date geteJoinDate() {
        return eJoinDate;
    }

    public void seteJoinDate(Date eJoinDate) {
        this.eJoinDate = eJoinDate;
    }

    public Long geteManagerId() {
        return eManagerId;
    }

    public void seteManagerId(Long eManagerId) {
        this.eManagerId = eManagerId;
    }

    public Long geteCreatedByHrId() {
        return eCreatedByHrId;
    }

    public void seteCreatedByHrId(Long eCreatedByHrId) {
        this.eCreatedByHrId = eCreatedByHrId;
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

    public String getePhone() {
        return ePhone;
    }

    public void setePhone(String ePhone) {
        this.ePhone = ePhone;
    }

    public Status getEstatus() {
        return estatus;
    }

    public void setEstatus(Status estatus) {
        this.estatus = estatus;
    }

    public Gender geteGender() {
        return eGender;
    }

    public void seteGender(Gender eGender) {
        this.eGender = eGender;
    }
}
