package com.hrms.ResponseDTO;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompanyLocationResponseDTO {

    private Long id;
    private String locationName;
    private String pincode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer allowedRadiusInMeters;
    private Long companyId;
//    private String companyName; // optional, for better response
    private  String companyCode;

    private  boolean is_active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

    public Integer getAllowedRadiusInMeters() { return allowedRadiusInMeters; }
    public void setAllowedRadiusInMeters(Integer allowedRadiusInMeters) { this.allowedRadiusInMeters = allowedRadiusInMeters; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

//    public String getCompanyName() { return companyName; }
//    public void setCompanyName(String companyName) { this.companyName = companyName; }

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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
