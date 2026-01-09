package com.hrms.ResponseDTO;

import com.hrms.enums.AttendanceStatus;

import java.time.LocalDate;

public class AttendanceDayResponse {

    private LocalDate date;
    private String attendanceStatus;
    private String punchInTime;
    private String punchOutTime;
    private String totalWorkedHours;





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

    public String getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public void setTotalWorkedHours(String totalWorkedHours) {
        this.totalWorkedHours = totalWorkedHours;
    }

    public AttendanceDayResponse(
            LocalDate date,
            Object punchInTime,
            Object punchOutTime,
            AttendanceStatus attendanceStatus
    ) {
        this.date = date;
        this.punchInTime = punchInTime != null ? punchInTime.toString() : null;
        this.punchOutTime = punchOutTime != null ? punchOutTime.toString() : null;
        this.attendanceStatus = attendanceStatus != null ? attendanceStatus.name() : null;
    }

}
