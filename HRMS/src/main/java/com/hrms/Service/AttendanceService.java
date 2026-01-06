package com.hrms.Service;

import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import com.hrms.enums.WorkType;

import java.util.List;

public interface AttendanceService {
    AttendanceResponseDTO punchIn(AttendanceRequestDTO attendanceRequestDTO);
    AttendanceResponseDTO punchOut(AttendanceRequestDTO attendanceRequestDTO);
    AttendanceResponseDTO getTodayAttendance();
//    AttendanceMonthlyResponseDTO getMonthlyAttendance(String employeeEmail, int month, int year);
//    AttendanceYearlyResponseDTO getYearlyAttendance(String employeeEmail, int year);


}
