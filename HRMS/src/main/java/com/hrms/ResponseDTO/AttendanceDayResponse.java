package com.hrms.ResponseDTO;

import com.hrms.enums.AttendanceStatus;
import com.hrms.enums.WorkType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceDayResponse {

    private LocalDate date;
    private String attendanceStatus;
    private String punchInTime;
    private String punchOutTime;
    private Double totalWorkedHours;

    public AttendanceDayResponse() {
    }

    public AttendanceDayResponse(AttendanceStatus status, LocalDate date, LocalDateTime checkIn, LocalDateTime checkOut, Double totalHours, WorkType workMode) {
    }

    public AttendanceDayResponse(LocalDate date, LocalTime localTime, LocalTime localTime1, AttendanceStatus status, Double totalHours) {
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getPunchInTime() {
        return punchInTime;
    }

    public void setPunchInTime(String punchInTime) {
        this.punchInTime = punchInTime;
    }

    public String getPunchOutTime() {
        return punchOutTime;
    }

    public void setPunchOutTime(String punchOutTime) {
        this.punchOutTime = punchOutTime;
    }

    public Double getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public void setTotalWorkedHours(Double totalWorkedHours) {
        this.totalWorkedHours = totalWorkedHours;
    }


    public AttendanceDayResponse(LocalDate date, String attendanceStatus, String punchInTime, String punchOutTime, Double totalWorkedHours) {
        this.date = date;
        this.attendanceStatus = attendanceStatus;
        this.punchInTime = punchInTime;
        this.punchOutTime = punchOutTime;
        this.totalWorkedHours = totalWorkedHours;
    }
    
    
}
