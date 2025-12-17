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
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eId;

    private  String companyCode;

    private String eName;

    private String eEmail;

    private String ePassword;

    private  String eDisgnation;
    @Column(precision = 15, scale = 2)
    private BigDecimal eSalary;

    private Date eJoinDate;

    private Long eManagerId;

    private  Long eCreatedByHrId;



    @Enumerated(value = EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String ePhone;

    @Enumerated(value = EnumType.STRING)
    private Status estatus;
    @Enumerated(value = EnumType.STRING)
    private Gender eGender;


    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;


    // ---------- UserDetails Methods ----------- //

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public  String getPassword() {
        return ePassword;
    }


    @Override
    public String getUsername() {
        return eEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //constructor

    public Employee() {
    }

    public Employee(String companyCode, String eName, String eEmail, String ePassword, String eDisgnation, BigDecimal eSalary, Date eJoinDate, Long eManagerId, Long eCreatedByHrId, Role role, Department department, String ePhone, Status estatus, Gender eGender, LocalDateTime createdAt, LocalDateTime updateAt, Company company) {
        this.companyCode = companyCode;
        this.eName = eName;
        this.eEmail = eEmail;
        this.ePassword = ePassword;
        this.eDisgnation = eDisgnation;
        this.eSalary = eSalary;
        this.eJoinDate = eJoinDate;
        this.eManagerId = eManagerId;
        this.eCreatedByHrId = eCreatedByHrId;
        this.role = role;
        this.department = department;
        this.ePhone = ePhone;
        this.estatus = estatus;
        this.eGender = eGender;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.company = company;
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

    public Long geteId() {
        return eId;
    }

    public void seteId(Long eId) {
        this.eId = eId;
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

    public String getePhone() {
        return ePhone;
    }

    public void setePhone(String ePhone) {
        this.ePhone = ePhone;
    }



    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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


}

