package com.hrms.ServiceImpl;

import com.hrms.Entity.Attendance;
import com.hrms.Entity.CompanyLocation;
import com.hrms.Entity.Employee;
import com.hrms.Mapper.AttendanceMapper;
import com.hrms.Repo.AttendanceRepo;
import com.hrms.Repo.CompanyLocationsRepo;
import com.hrms.Repo.EmpRepo;
import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceDayResponse;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import com.hrms.ResponseDTO.AttendanceSummaryResponse;
import com.hrms.ResponseDTO.AttendanceTodayResponse;
import com.hrms.Service.AttendanceService;
import com.hrms.Utility.GeoUtil;
import com.hrms.enums.AttendanceStatus;
import com.hrms.enums.WorkType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private EmpRepo empRepo;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private AttendanceRepo attendanceRepo;




    @Override
    public AttendanceResponseDTO punchIn(AttendanceRequestDTO attendanceRequestDTO) {
        //1. Taken email
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // 2. Employee fetch kiya;
        Employee employee = empRepo.findByEEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN, "Employee not found"
                ));


        // System.out.println("Emplye daata :: "+employee.getEId()+" "+employee.getCompanyCode());
// 3. Check: Kya aaj ki attendance pehle se lag chuki hai?
        attendanceRepo.findByEmployeeAndDate(employee, LocalDate.now())
                .ifPresent(a -> {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Attendance already marked for today"
                    );
                });

        //4. Get employee's assigned company location
        CompanyLocation location = employee.getCompanyLocation();
        if (location == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Employee location not assigned"
            );
        }
        //5. Validate GPS distance
        if (attendanceRequestDTO.getWorkMode() == WorkType.ONSITE) {
            double empLat = attendanceRequestDTO.getLatitude().doubleValue();
            double empLng = attendanceRequestDTO.getLongitude().doubleValue();

            double officeLat = location.getLatitude().doubleValue();
            double officeLng = location.getLongitude().doubleValue();
            int allowedRadius = location.getAllowedRadiusInMeters();

            double distance = GeoUtil.calculateDistanceInMeters(
                    empLat, empLng,
                    officeLat, officeLng
            );

            if (distance > allowedRadius) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "You are outside the allowed office location radius"
                );
            }
        }

        Attendance attendance = attendanceMapper.toEntity(attendanceRequestDTO); // maps workMode, lat, long

        attendance.setEmployee(employee);         // fetched from DB
        attendance.setCompanyLocation(location);  // fetched from employee
        attendance.setDate(LocalDate.now());      // server-generated
        attendance.setCheckIn(LocalDateTime.now());// server-generated
        attendance.setStatus(AttendanceStatus.PRESENT);

//        System.out.println("Attendance Saved:");
//        System.out.println("ID: " + attendance.getAttendanceId());
//        System.out.println("Employee: " + attendance.getEmployee().getEName() + " (ID: " + attendance.getEmployee().getEId() + ")");
//        System.out.println("Company Location: " + attendance.getCompanyLocation().getLocationCode());
//        System.out.println("Date: " + attendance.getDate());
//        System.out.println("Check-in: " + attendance.getCheckIn());
//        System.out.println("Latitude: " + attendance.getLatitude());
//        System.out.println("Longitude: " + attendance.getLongitude());
//        System.out.println("Status: " + attendance.getStatus());

       Attendance savedAttendance = attendanceRepo.save(attendance);
           //   return null;
       return attendanceMapper.toDto(savedAttendance);
    }

    @Override
    public AttendanceResponseDTO punchOut(AttendanceRequestDTO attendanceRequestDTO) {

        // 1 Get logged-in employee
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Employee employee = empRepo.findByEEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "Authenticated user is not registered as an employee"
                ));

        // 2 Get today's attendance
        Attendance attendance = attendanceRepo.findByEmployeeAndDate(employee, LocalDate.now())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "You have not punched in today"
                ));

        // 3 Optional: Validate GPS distance for ON_SITE
        CompanyLocation location = employee.getCompanyLocation();
        if (location == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Employee location not assigned"
            );
        }

        if (attendanceRequestDTO.getWorkMode() == WorkType.ONSITE) {
            double empLat = attendanceRequestDTO.getLatitude().doubleValue();
            double empLng = attendanceRequestDTO.getLongitude().doubleValue();

            double officeLat = location.getLatitude().doubleValue();
            double officeLng = location.getLongitude().doubleValue();

            int allowedRadius = location.getAllowedRadiusInMeters();
            double distance = GeoUtil.calculateDistanceInMeters(empLat, empLng, officeLat, officeLng);

            if (distance > allowedRadius) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "You are outside the allowed office location radius"
                );
            }
        }

        // 4 Set check-out and work mode info
        attendance.setCheckOut(LocalDateTime.now());
        attendance.setWorkMode(attendanceRequestDTO.getWorkMode());
        attendance.setLatitude(attendanceRequestDTO.getLatitude());
        attendance.setLongitude(attendanceRequestDTO.getLongitude());

        LocalDateTime checkInTime = attendance.getCheckIn();
        if (checkInTime != null) {
            long minutesWorked = java.time.Duration.between(checkInTime, attendance.getCheckOut()).toMinutes();
            double hoursWorked = (long) (minutesWorked / 60.0); // convert to decimal hours
            // System.out.println(" checout :::___>> "+hoursWorked);
            attendance.setTotalHours(hoursWorked);     // make sure your Attendance entity has a totalHours field
        }


        // 5 Save attendance
        Attendance savedAttendance = attendanceRepo.save(attendance);




        // 6 Return response
       return attendanceMapper.toDto(savedAttendance);

    }


    @Override
    public AttendanceDayResponse getTodayAttendance(String email) {

//        // 1 Get logged-in employee
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String email = auth.getName();

        Employee employee = empRepo.findByEEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "Authenticated user is not registered as an employee"
                ));
       Long  empid=  employee.getEId();

      Attendance attendance= attendanceRepo.findByEmployeeAndDate(employee,LocalDate.now())
              .orElseThrow(() -> new ResponseStatusException(
                      HttpStatus.NOT_FOUND,
                      "Not able to fetch the data from the Attendance Because you Not MARk the Attendance Today"
              ));

        System.out.println("Making Attendemce emp is :: "+" "+ employee.getEName() +" "+attendance.getStatus()+" "+attendance.getWorkMode()  );

        return attendanceMapper.toDayDto(attendance);
    }

    @Override
    public AttendanceSummaryResponse getMyAttendanceByMonth( String email ,int month, int year) {
//
//        Employee employee = empRepo.findByEEmail(email)
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.FORBIDDEN,
//                        "Authenticated user is not registered as an employee"
//                ));
//        Long  empid=  employee.getEId();
//        //1. Set the Full date
//        YearMonth ym = YearMonth.of(year, month);
//        LocalDate start = ym.atDay(1);
//        LocalDate end = ym.atEndOfMonth();
//
//        //2. Get The Attendance records dfor month
//        List<Attendance> records =
//                attendanceRepo.findByEmployeeAndDateBetween(employee, start, end);
//
////        records.forEach(a ->
////                System.out.println(a.getDate() + " - " + a.getCheckIn() + " - " + a.getCheckOut() + " - " + a.getStatus())
////        );
//
//
//
//        //3.Convert attendace list to map
//        Map<LocalDate ,Attendance> attendanceMap =records.stream()
//                .collect(Collectors.toMap(a->a.getDate(),a->a));
//
////        attendanceMap.forEach((k,v) ->
////                System.out.println(k + " -> " + v.getCheckIn() + " - " + v.getCheckOut() + " - " + v.getStatus())
////        );
//        LocalDate joiningDate = employee.getEJoinDate()
//                .toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//
//        // 4. Build daily attendance
//
//        List<AttendanceDayResponse> dailyList =
//                start.datesUntil(end.plusDays(1))
//                        .map(date->{
//                            Attendance a = attendanceMap.get(date);
//
//                            if (date.isBefore(joiningDate)) {
//                                return new AttendanceDayResponse(date, null, null, AttendanceStatus.NOT_JOINED);
//                            }return (a != null)
//                                        ? new AttendanceDayResponse(date, a.getCheckIn(), a.getCheckOut(), a.getStatus())
//                                        : new AttendanceDayResponse(date, null, null, AttendanceStatus.ABSENT);
//                        })
//                        .toList();
//
////        dailyList.forEach(a->
////                System.out.println(a.getDate()+" "+a.getPunchInTime()+" "+a.getPunchOutTime()+" "+a.getAttendanceStatus()));
//
//        return new AttendanceSummaryResponse(
//                employee.getEId(),
//                employee.getEName(),
//                month,
//                year,
//                dailyList
//        );
        return  null;
    }

    @Override
    public AttendanceSummaryResponse getMyAttendanceByYear( String email , int year) {
        return null;
    }

    @Override
    public List<AttendanceTodayResponse> getTodayAttendanceByCompany(String companyShortCode, Long requestedByUserId) {
        return null;
    }

    @Override
    public List<AttendanceSummaryResponse> getAttendanceByMonthForCompany(String companyShortCode, int month, int year, Long requestedByUserId) {
        return null;
    }

    @Override
    public List<AttendanceSummaryResponse> getAttendanceByYearForCompany(String companyShortCode, int year, Long requestedByUserId) {
        return null;
    }
}
