package com.hrms.Service;

import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import com.hrms.enums.WorkType;

import java.util.List;

public interface AttendanceService {
    AttendanceResponseDTO punchIn(Long locationId, WorkType workMode, String employeeEmail);
    AttendanceResponseDTO punchOut(Long locationId, String employeeEmail);
    AttendanceResponseDTO getTodayAttendance(String employeeEmail);
//    AttendanceMonthlyResponseDTO getMonthlyAttendance(String employeeEmail, int month, int year);
//    AttendanceYearlyResponseDTO getYearlyAttendance(String employeeEmail, int year);


}
