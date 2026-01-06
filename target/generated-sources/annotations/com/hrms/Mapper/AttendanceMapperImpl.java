package com.hrms.Mapper;

import com.hrms.Entity.Attendance;
import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-06T19:40:16+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public Attendance toEntity(AttendanceRequestDTO attendanceRequestDTO) {
        if ( attendanceRequestDTO == null ) {
            return null;
        }

        Attendance attendance = new Attendance();

        attendance.setLatitude( attendanceRequestDTO.getLatitude() );
        attendance.setLongitude( attendanceRequestDTO.getLongitude() );
        attendance.setIpAddress( attendanceRequestDTO.getIpAddress() );
        attendance.setWorkMode( attendanceRequestDTO.getWorkMode() );

        return attendance;
    }

    @Override
    public AttendanceResponseDTO toDto(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        AttendanceResponseDTO attendanceResponseDTO = new AttendanceResponseDTO();

        attendanceResponseDTO.setCheckIn( attendance.getCheckIn() );
        attendanceResponseDTO.setCheckOut( attendance.getCheckOut() );
        attendanceResponseDTO.setDate( attendance.getDate() );
        attendanceResponseDTO.setAttendanceId( attendance.getAttendanceId() );
        attendanceResponseDTO.setLatitude( attendance.getLatitude() );
        attendanceResponseDTO.setLongitude( attendance.getLongitude() );
        attendanceResponseDTO.setIpAddress( attendance.getIpAddress() );
        attendanceResponseDTO.setStatus( attendance.getStatus() );
        attendanceResponseDTO.setWorkMode( attendance.getWorkMode() );
        attendanceResponseDTO.setCreatedAt( attendance.getCreatedAt() );
        attendanceResponseDTO.setUpdatedAt( attendance.getUpdatedAt() );

        return attendanceResponseDTO;
    }
}
