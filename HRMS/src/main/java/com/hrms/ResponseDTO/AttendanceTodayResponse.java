package com.hrms.ResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceTodayResponse {

    private Long employeeId;
    private String employeeCode;
    private String employeeName;

    private LocalDate attendanceDate;

    private LocalDateTime punchInTime;
    private LocalDateTime punchOutTime;

    private String workMode;      // WFH / WFO
    private String attendanceStatus; // PRESENT / ABSENT / HALF_DAY

    private String totalWorkedHours;


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public LocalDateTime getPunchInTime() {
        return punchInTime;
    }

    public void setPunchInTime(LocalDateTime punchInTime) {
        this.punchInTime = punchInTime;
    }

    public LocalDateTime getPunchOutTime() {
        return punchOutTime;
    }

    public void setPunchOutTime(LocalDateTime punchOutTime) {
        this.punchOutTime = punchOutTime;
    }

    public String getWorkMode() {
        return workMode;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public void setTotalWorkedHours(String totalWorkedHours) {
        this.totalWorkedHours = totalWorkedHours;
    }
}
