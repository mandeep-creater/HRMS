package com.hrms.ResponseDTO;



import java.time.LocalDateTime;
import java.util.List;

public class DepartmentResponseDTO {

    private Long id;

    private String departmentName;

    private boolean isActive;

    private  String companyCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long companyId;        // or companyName

//    private List<Long> employeeIds; // optional

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

//    public List<Long> getEmployeeIds() {
//        return employeeIds;
//    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

//    public void setEmployeeIds(List<Long> employeeIds) {
//        this.employeeIds = employeeIds;
//    }
}
