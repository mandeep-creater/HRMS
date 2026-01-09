package com.hrms.ResponseDTO;

import java.util.List;

public class AttendanceSummaryResponse {
    private Long employeeId;
    private String employeeCode;
    private String employeeName;

    private int month;
    private int year;

    private int totalWorkingDays;
    private int presentDays;
    private int absentDays;
    private int halfDays;

    private List<AttendanceDayResponse> dailyAttendance;

    public AttendanceSummaryResponse(Long eId, String eName, int month, int year, List<AttendanceDayResponse> dailyList) {
    }
}
