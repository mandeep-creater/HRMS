package com.hrms.ResponseDTO;



import java.util.List;

public class AttendanceSummaryResponse {

    private Long employeeId;
    private String employeeName;
    private int month;
    private int year;
    private List<AttendanceDayResponse> dailyAttendance;

    public AttendanceSummaryResponse() {
    }

    // Constructor you are already using
    public AttendanceSummaryResponse(
            Long employeeId,
            String employeeName,
            int month,
            int year,
            List<AttendanceDayResponse> dailyAttendance
    ) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.month = month;
        this.year = year;
        this.dailyAttendance = dailyAttendance;
    }

    // ✅ GETTERS (MOST IMPORTANT)

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public List<AttendanceDayResponse> getDailyAttendance() {
        return dailyAttendance;
    }
}
