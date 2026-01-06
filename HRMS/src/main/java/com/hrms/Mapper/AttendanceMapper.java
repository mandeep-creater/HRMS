package com.hrms.Mapper;

import com.hrms.Entity.Attendance;
import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.ResponseEntity;

import javax.swing.*;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "companyLocation", ignore = true)
    Attendance toEntity(AttendanceRequestDTO attendanceRequestDTO);

    //@Mapping(source = "companyLocation.locationName", target = "locationName")
    @Mapping(target = "checkIn")
    @Mapping(target = "checkOut")
    @Mapping(target = "date")
    AttendanceResponseDTO toDto(Attendance attendance);

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
