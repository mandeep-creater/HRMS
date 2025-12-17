package com.hrms.ResponseDTO;

import com.hrms.enums.Gender;
import com.hrms.enums.Role;
import com.hrms.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class EmployeeResponseDTO {

    private Long eId;

    private String companyCode;

    private String eName;

    private String eEmail;

    private String eDisgnation;

    private BigDecimal eSalary;

    private Date eJoinDate;

    private Long eManagerId;

    private Long eCreatedByHrId;

    private Role role;

    private Long departmentId;     // or departmentName

    private String ePhone;

    private Status estatus;

    private Gender eGender;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private Long companyId;

    public Long geteId() {
        return eId;
    }

    public void seteId(Long eId) {
        this.eId = eId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
