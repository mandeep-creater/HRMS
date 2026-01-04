package com.hrms.RequestsDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.hrms.enums.AttendanceStatus;



import com.hrms.enums.WorkType;


public class AttendanceRequestDTO {

   // @NotNull(message = "Date is required")
    private LocalDate date;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private BigDecimal latitude;

    private BigDecimal longitude;

//    @Size(max = 50, message = "IP Address cannot exceed 50 characters")
    private String ipAddress;

    private AttendanceStatus status;

//    @NotNull(message = "Work mode is required")
    private WorkType workMode;

//    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    private String email;

//    @NotNull(message = "Company location ID is required")
    private Long companyLocationId;

    // Getters & Setters

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalDateTime getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDateTime checkIn) { this.checkIn = checkIn; }

    public LocalDateTime getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDateTime checkOut) { this.checkOut = checkOut; }

    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public AttendanceStatus getStatus() { return status; }
    public void setStatus(AttendanceStatus status) { this.status = status; }

    public WorkType getWorkMode() { return workMode; }
    public void setWorkMode(WorkType workMode) { this.workMode = workMode; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Long getCompanyLocationId() { return companyLocationId; }
    public void setCompanyLocationId(Long companyLocationId) { this.companyLocationId = companyLocationId; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
