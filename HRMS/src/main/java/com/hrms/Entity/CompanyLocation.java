package com.hrms.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.processing.Pattern;

import java.math.BigDecimal;

@Entity
@Table(name = "company_locations")
public class CompanyLocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locationCode;

    private String locationShortCode;

    private String locationName;

    private String city;

    @Column(length = 6)
    private String pincode;

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    boolean is_active = true;

    private Integer allowedRadiusInMeters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public CompanyLocation() {
    }

    public CompanyLocation(String locationCode, String locationShortCode, String locationName, String city, String pincode, BigDecimal latitude, BigDecimal longitude, boolean is_active, Integer allowedRadiusInMeters, Company company) {
        this.locationCode = locationCode;
        this.locationShortCode = locationShortCode;
        this.locationName = locationName;
        this.city = city;
        this.pincode = pincode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.is_active = is_active;
        this.allowedRadiusInMeters = allowedRadiusInMeters;
        this.company = company;
    }

    public CompanyLocation(String locationName, String pincode, BigDecimal latitude, BigDecimal longitude, Boolean is_active, Integer allowedRadiusInMeters, Company company) {
        this.locationName = locationName;
        this.pincode = pincode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.is_active = is_active;
        this.allowedRadiusInMeters = allowedRadiusInMeters;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Integer getAllowedRadiusInMeters() {
        return allowedRadiusInMeters;
    }

    public void setAllowedRadiusInMeters(Integer allowedRadiusInMeters) {
        this.allowedRadiusInMeters = allowedRadiusInMeters;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationShortCode() {
        return locationShortCode;
    }

    public void setLocationShortCode(String locationShortCode) {
        this.locationShortCode = locationShortCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
