package com.hrms.RequestsDTO;


import java.math.BigDecimal;

public class CompanyLocationRequestDTO {

    //@NotBlank(message = "Location name is required")
    private String locationName;

//    @NotBlank(message = "Pincode is required")
//    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid Indian pincode")
    private String pincode;

//    @NotNull(message = "Latitude is required")
//    @DecimalMin(value = "-90.0", message = "Latitude must be >= -90")
//    @DecimalMax(value = "90.0", message = "Latitude must be <= 90")
    private BigDecimal latitude;

//    @NotNull(message = "Longitude is required")
//    @DecimalMin(value = "-180.0", message = "Longitude must be >= -180")
//    @DecimalMax(value = "180.0", message = "Longitude must be <= 180")
    private BigDecimal longitude;

//    @NotNull(message = "Allowed radius is required")
//    @Min(value = 0, message = "Radius cannot be negative")
    private Integer allowedRadiusInMeters;

//    @NotNull(message = "Company ID is required")
    private Long companyId;

    //private String companyCode;

    // Getters & Setters

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

//    public String getCompanyCode() {
//        return companyCode;
//    }
//
//    public void setCompanyCode(String companyCode) {
//        this.companyCode = companyCode;
//    }
}
