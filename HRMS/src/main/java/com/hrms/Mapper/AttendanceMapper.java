package com.hrms.Mapper;

import com.hrms.Entity.Attendance;
import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceDayResponse;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import com.hrms.ResponseDTO.AttendanceSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.ResponseEntity;

import javax.swing.*;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    @Mapping(source = "workMode", target = "workMode")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    Attendance toEntity(AttendanceRequestDTO attendanceRequestDTO);

    //@Mapping(source = "companyLocation.locationName", target = "locationName")
    @Mapping(source = "employee.EName", target = "employeeName")
    @Mapping(source = "employee.EId", target = "employeeId")
    @Mapping(source = "employee.email",target = "email")
    @Mapping(source = "companyLocation.locationCode", target = "companyLocationCode")
    @Mapping(source = "companyLocation.id", target = "companyLocationId")
    @Mapping(source = "companyLocation.locationName" ,target="companyLocationName")
    @Mapping(source = "totalHours", target = "toatlworkingHour")

    AttendanceResponseDTO toDto(Attendance attendance);

    @Mapping(source = "status", target = "attendanceStatus")
    @Mapping(source = "checkIn", target = "punchInTime")
    @Mapping(source = "checkOut",target = "punchOutTime")
    @Mapping(source = "totalHours",target="totalWorkedHours")
    AttendanceDayResponse toDayDto(Attendance attendance);





    default String formatDateTime(java.time.LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    // Helper method for Date
    default String formatDate(java.time.LocalDate date) {
        if (date == null) return null;
        return date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
