package com.hrms.ResponseDTO;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrms.enums.AttendanceStatus;
import com.hrms.enums.WorkType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;



public class AttendanceResponseDTO {

        private Long attendanceId;
        private LocalDate date;
//    @JsonFormat(pattern = "HH:mm:ss")
        private LocalDateTime checkIn;
//    @JsonFormat(pattern = "HH:mm:ss")
        private LocalDateTime checkOut;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private String ipAddress;
        private AttendanceStatus status;
        private WorkType workMode;
        private Long employeeId;
        private String employeeName; // optional for readability

        private String email;
        private Long companyLocationId;
        private String companyLocationName; // optional

    private  double toatlworkingHour;

    private  String companyLocationCode;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;


    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public WorkType getWorkMode() {
        return workMode;
    }

    public void setWorkMode(WorkType workMode) {
        this.workMode = workMode;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCompanyLocationId() {
        return companyLocationId;
    }

    public void setCompanyLocationId(Long companyLocationId) {
        this.companyLocationId = companyLocationId;
    }

    public String getCompanyLocationName() {
        return companyLocationName;
    }

    public void setCompanyLocationName(String companyLocationName) {
        this.companyLocationName = companyLocationName;
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

    public String getCompanyLocationCode() {
        return companyLocationCode;
    }

    public void setCompanyLocationCode(String companyLocationCode) {
        this.companyLocationCode = companyLocationCode;
    }

    public double getToatlworkingHour() {
        return toatlworkingHour;
    }

    public void setToatlworkingHour(double toatlworkingHour) {
        this.toatlworkingHour = toatlworkingHour;
    }
}