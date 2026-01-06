package com.hrms.RequestsDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hrms.enums.AttendanceStatus;



import com.hrms.enums.WorkType;


public class AttendanceRequestDTO {

   // @NotNull(message = "Date is required")


    private BigDecimal latitude;

    private BigDecimal longitude;

//    @Size(max = 50, message = "IP Address cannot exceed 50 characters")
    private String ipAddress;


//    @NotNull(message = "Work mode is required")
    private WorkType workMode;

//    @NotNull(message = "Employee ID is required")


    // Getter   s & Setters


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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public WorkType getWorkMode() {
        return workMode;
    }

    public void setWorkMode(WorkType workMode) {
        this.workMode = workMode;
    }
}
