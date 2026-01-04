package com.hrms.Entity;

import com.hrms.enums.Gender;
import com.hrms.enums.Role;
import com.hrms.enums.Status;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eId;

    private String companyCode;

    private String eName;
    @Column(name = "e_email")
    private String email;

    private String ePassword;

    private String eDisgnation;

    @Column(precision = 15, scale = 2)
    private BigDecimal eSalary;

    private Date eJoinDate;

    private Long eManagerId;

    private Long eCreatedByHrId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String ePhone;

    @Enumerated(EnumType.STRING)
    private Status estatus;

    @Enumerated(EnumType.STRING)
    private Gender eGender;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    // ---------------- UserDetails ----------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return ePassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    // ---------------- Getters & Setters ----------------

    public Long getEId() {
        return eId;
    }

    public void setEId(Long eId) {
        this.eId = eId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEPhone() {
        return ePhone;
    }

    public void setEPhone(String ePhone) {
        this.ePhone = ePhone;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
