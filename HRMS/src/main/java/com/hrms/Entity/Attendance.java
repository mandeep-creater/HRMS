package com.hrms.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrms.enums.AttendanceStatus;
import com.hrms.enums.WorkType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="attendance")
public class Attendance extends  BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long attendanceId;

        private LocalDate date;

        private Double totalHours;

        @JsonFormat(pattern = "HH:mm:ss")
        private LocalDateTime checkIn;
        @JsonFormat(pattern = "HH:mm:ss")
        private LocalDateTime checkOut;

        private BigDecimal latitude;

        private BigDecimal longitude;

        private String ipAddress;

        @Enumerated(EnumType.STRING)
        private AttendanceStatus status;


        @Enumerated(EnumType.STRING)
        private WorkType workMode;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "company_location_id", nullable = false)
        private CompanyLocation companyLocation;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "employee_id",nullable = false)
        private Employee employee;

        public Attendance() {
        }


        // Getters & Setters


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


        public Employee getEmployee() {
                return employee;
        }

        public void setEmployee(Employee employee) {
                this.employee = employee;
        }


        public Long getAttendanceId() {
                return attendanceId;
        }

//        public void setAttendanceId(Long attendanceId) {
//                this.attendanceId = attendanceId;
//        }

        public WorkType getWorkMode() {
                return workMode;
        }

        public void setWorkMode(WorkType workMode) {
                this.workMode = workMode;
        }

        public CompanyLocation getCompanyLocation() {
                return companyLocation;
        }

        public void setCompanyLocation(CompanyLocation companyLocation) {
                this.companyLocation = companyLocation;
        }



        public Double getTotalHours() {
                return totalHours;
        }

        public void setTotalHours(Double totalHours) {
                this.totalHours = totalHours;
        }

        public Attendance(LocalDate date, Double totalHours, LocalDateTime checkIn, LocalDateTime checkOut, BigDecimal latitude, BigDecimal longitude, String ipAddress, AttendanceStatus status, WorkType workMode, CompanyLocation companyLocation, Employee employee) {
                this.date = date;
                this.totalHours = totalHours;
                this.checkIn = checkIn;
                this.checkOut = checkOut;
                this.latitude = latitude;
                this.longitude = longitude;
                this.ipAddress = ipAddress;
                this.status = status;
                this.workMode = workMode;
                this.companyLocation = companyLocation;
                this.employee = employee;
        }
}
