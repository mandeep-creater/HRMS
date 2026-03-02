package com.hrms.Mapper;

import com.hrms.Entity.Attendance;
import com.hrms.Entity.CompanyLocation;
import com.hrms.Entity.Employee;
import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceDayResponse;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T20:53:16+0530",
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

        attendance.setWorkMode( attendanceRequestDTO.getWorkMode() );
        attendance.setLatitude( attendanceRequestDTO.getLatitude() );
        attendance.setLongitude( attendanceRequestDTO.getLongitude() );
        attendance.setIpAddress( attendanceRequestDTO.getIpAddress() );

        return attendance;
    }

    @Override
    public AttendanceResponseDTO toDto(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        AttendanceResponseDTO attendanceResponseDTO = new AttendanceResponseDTO();

        attendanceResponseDTO.setEmployeeName( attendanceEmployeeEName( attendance ) );
        attendanceResponseDTO.setEmployeeId( attendanceEmployeeEId( attendance ) );
        attendanceResponseDTO.setEmail( attendanceEmployeeEmail( attendance ) );
        attendanceResponseDTO.setCompanyLocationCode( attendanceCompanyLocationLocationCode( attendance ) );
        attendanceResponseDTO.setCompanyLocationId( attendanceCompanyLocationId( attendance ) );
        attendanceResponseDTO.setCompanyLocationName( attendanceCompanyLocationLocationName( attendance ) );
        if ( attendance.getTotalHours() != null ) {
            attendanceResponseDTO.setToatlworkingHour( attendance.getTotalHours() );
        }
        attendanceResponseDTO.setAttendanceId( attendance.getAttendanceId() );
        attendanceResponseDTO.setDate( attendance.getDate() );
        attendanceResponseDTO.setCheckIn( attendance.getCheckIn() );
        attendanceResponseDTO.setCheckOut( attendance.getCheckOut() );
        attendanceResponseDTO.setLatitude( attendance.getLatitude() );
        attendanceResponseDTO.setLongitude( attendance.getLongitude() );
        attendanceResponseDTO.setIpAddress( attendance.getIpAddress() );
        attendanceResponseDTO.setStatus( attendance.getStatus() );
        attendanceResponseDTO.setWorkMode( attendance.getWorkMode() );
        attendanceResponseDTO.setCreatedAt( attendance.getCreatedAt() );
        attendanceResponseDTO.setUpdatedAt( attendance.getUpdatedAt() );

        return attendanceResponseDTO;
    }

    @Override
    public AttendanceDayResponse toDayDto(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        AttendanceDayResponse attendanceDayResponse = new AttendanceDayResponse();

        if ( attendance.getStatus() != null ) {
            attendanceDayResponse.setAttendanceStatus( attendance.getStatus().name() );
        }
        attendanceDayResponse.setPunchInTime( formatDateTime( attendance.getCheckIn() ) );
        attendanceDayResponse.setPunchOutTime( formatDateTime( attendance.getCheckOut() ) );
        attendanceDayResponse.setTotalWorkedHours( attendance.getTotalHours() );
        attendanceDayResponse.setDate( attendance.getDate() );

        return attendanceDayResponse;
    }

    private String attendanceEmployeeEName(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }
        Employee employee = attendance.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String eName = employee.getEName();
        if ( eName == null ) {
            return null;
        }
        return eName;
    }

    private Long attendanceEmployeeEId(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }
        Employee employee = attendance.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Long eId = employee.getEId();
        if ( eId == null ) {
            return null;
        }
        return eId;
    }

    private String attendanceEmployeeEmail(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }
        Employee employee = attendance.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String email = employee.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String attendanceCompanyLocationLocationCode(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }
        CompanyLocation companyLocation = attendance.getCompanyLocation();
        if ( companyLocation == null ) {
            return null;
        }
        String locationCode = companyLocation.getLocationCode();
        if ( locationCode == null ) {
            return null;
        }
        return locationCode;
    }

    private Long attendanceCompanyLocationId(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }
        CompanyLocation companyLocation = attendance.getCompanyLocation();
        if ( companyLocation == null ) {
            return null;
        }
        Long id = companyLocation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String attendanceCompanyLocationLocationName(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }
        CompanyLocation companyLocation = attendance.getCompanyLocation();
        if ( companyLocation == null ) {
            return null;
        }
        String locationName = companyLocation.getLocationName();
        if ( locationName == null ) {
            return null;
        }
        return locationName;
    }
}
