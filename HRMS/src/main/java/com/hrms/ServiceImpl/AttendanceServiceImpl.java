package com.hrms.ServiceImpl;

import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import com.hrms.Service.AttendanceService;
import com.hrms.enums.WorkType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Override
    public AttendanceResponseDTO punchIn(Long locationId, WorkType workMode, String employeeEmail) {
        return null;
    }

    @Override
    public AttendanceResponseDTO punchOut(Long locationId, String employeeEmail) {
        return null;
    }

    @Override
    public AttendanceResponseDTO getTodayAttendance(String employeeEmail) {
        return null;
    }
}
