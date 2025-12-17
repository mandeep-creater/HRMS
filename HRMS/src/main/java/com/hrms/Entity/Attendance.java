package com.hrms.Entity;

import com.hrms.enums.AttendanceStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="attendance")
public class Attendance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long attendanceId;

        private LocalDate date;

        private LocalDateTime checkIn;

        private LocalDateTime checkOut;

        private Double latitude;

        private Double longitude;

        private String ipAddress;

        @Enumerated(EnumType.STRING)
        private AttendanceStatus status;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "employee_id")
        private Employee employee;

        public Attendance() {
        }

        @PrePersist
        public void prePersist() {
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        public void preUpdate() {
            this.updatedAt = LocalDateTime.now();
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

        public Double getLatitude() {
                return latitude;
        }

        public void setLatitude(Double latitude) {
                this.latitude = latitude;
        }

        public Double getLongitude() {
                return longitude;
        }

        public void setLongitude(Double longitude) {
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

        public Employee getEmployee() {
                return employee;
        }

        public void setEmployee(Employee employee) {
                this.employee = employee;
        }

        public Attendance(LocalDate date, LocalDateTime checkIn, LocalDateTime checkOut, Double latitude, Double longitude, String ipAddress, AttendanceStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, Employee employee) {
                this.date = date;
                this.checkIn = checkIn;
                this.checkOut = checkOut;
                this.latitude = latitude;
                this.longitude = longitude;
                this.ipAddress = ipAddress;
                this.status = status;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                this.employee = employee;
        }

}
