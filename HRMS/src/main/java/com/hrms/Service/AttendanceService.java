package com.hrms.Service;

import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceDayResponse;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import com.hrms.ResponseDTO.AttendanceSummaryResponse;
import com.hrms.ResponseDTO.AttendanceTodayResponse;
import com.hrms.enums.WorkType;

import java.util.List;

public interface AttendanceService {
    AttendanceResponseDTO punchIn(AttendanceRequestDTO attendanceRequestDTO);
    AttendanceResponseDTO punchOut(AttendanceRequestDTO attendanceRequestDTO);
    AttendanceDayResponse getTodayAttendance(String email);


    AttendanceSummaryResponse getMyAttendanceByMonth(String email, int month, int year);
    AttendanceSummaryResponse getMyAttendanceByYear(String email ,  int year);

    // HR / Admin (company-wise)
    List<AttendanceTodayResponse> getTodayAttendanceByCompany(
            String companyShortCode, Long requestedByUserId);

    List<AttendanceSummaryResponse> getAttendanceByMonthForCompany(
            String companyShortCode, int month, int year, Long requestedByUserId);

    List<AttendanceSummaryResponse> getAttendanceByYearForCompany(
            String companyShortCode, int year, Long requestedByUserId);


}
