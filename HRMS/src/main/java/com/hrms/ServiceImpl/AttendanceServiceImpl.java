package com.hrms.ServiceImpl;

import com.hrms.Entity.Attendance;
import com.hrms.Entity.CompanyLocation;
import com.hrms.Entity.Employee;
import com.hrms.Mapper.AttendanceMapper;
import com.hrms.Repo.AttendanceRepo;
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

import java.time.*;
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
    public AttendanceDayResponse getTodayAttendance(String email ) {

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
    public AttendanceSummaryResponse getMyAttendanceByMonth(String email, int month, int year) {

        Employee employee = empRepo.findByEEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "Authenticated user is not registered as an employee"
                ));

        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // 1. Get Records
        List<Attendance> records = attendanceRepo.findByEmployeeAndDateBetween(employee, start, end);

        // 2. Convert to Map (Safe)
        Map<LocalDate, Attendance> attendanceMap = records.stream()
                .collect(Collectors.toMap(Attendance::getDate, a -> a, (existing, replacement) -> existing));

        LocalDate joiningDate = employee.getEJoinDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate today = LocalDate.now();

        // 3. Validations
        if (start.isAfter(today.withDayOfMonth(1))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Future attendance cannot be viewed");
        }

        if (end.isBefore(joiningDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not joined yet");
        }

        LocalDate effectiveEndDate = (start.getYear() == today.getYear() && start.getMonth() == today.getMonth())
                ? today : end;

        // 4. Build daily attendance (The Null-Safe Way)
        List<AttendanceDayResponse> dailyList = start.datesUntil(effectiveEndDate.plusDays(1))
                .map(date -> {
                    if (date.isBefore(joiningDate)) {
                        return new AttendanceDayResponse(date, "NOT_JOINED", "--", "--", 0.0);
                    }

                    Attendance a = attendanceMap.get(date);
                    if (a != null) {
                        // Safe formatting for the JSON response
                        String in = (a.getCheckIn() != null) ? a.getCheckIn().toLocalTime().toString() : "00:00:00";
                        String out = (a.getCheckOut() != null) ? a.getCheckOut().toLocalTime().toString() : "Pending";
                        Double hours = (a.getTotalHours() != null) ? a.getTotalHours() : 0.0;

                        return new AttendanceDayResponse(date,a.getStatus().toString(),  in, out,  hours);
                    }

                    return new AttendanceDayResponse(date, "ABSENT", "00:00:00", "00:00:00", 0.0);
                })
                .toList();

        return new AttendanceSummaryResponse(employee.getEId(), employee.getEName(), month, year, dailyList);
    }

  /*  @Override
    public List<AttendanceSummaryResponse> getMyAttendanceByYear(String email, int year) {

        LocalDate today = LocalDate.now();

        // 1. Future year check
        if (year > today.getYear()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Future year attendance cannot be viewed");
        }

        Employee employee = empRepo.findByEEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "User not registered"));

        // 2. Joining date check
        LocalDate joiningDate = employee.getEJoinDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (year < joiningDate.getYear()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee was not part of the organization in this year");
        }

        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = (year == today.getYear()) ? today : LocalDate.of(year, 12, 31);

        // 3. Fetch data
        List<Attendance> attendanceList = attendanceRepo.findByEmployeeAndDateBetween(employee, startOfYear, endOfYear);

        // 4. Group by month
        Map<Month, List<Attendance>> attendanceByMonth = attendanceList.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getDate().getMonth(),
                        TreeMap::new,
                        Collectors.toList()
                ));

        // 5. Map to Response DTOs (No Casting needed here!)
        return attendanceByMonth.entrySet().stream()
                .map(entry -> {
                    Month month = entry.getKey();

                    List<AttendanceDayResponse> dailyResponses = entry.getValue().stream()
                            .sorted(Comparator.comparing(Attendance::getDate))
                            .map(a -> new AttendanceDayResponse(
                                    a.getStatus(),
                                    a.getDate(),
                                    a.getCheckIn(),
                                    a.getCheckOut(),
                                    a.getTotalHours(),
                                    a.getWorkMode()
                            ))
                            .collect(Collectors.toList());

                    // Match your DTO Constructor: (ID, Name, Month, Year, List)
                    return new AttendanceSummaryResponse(
                            employee.getEId(),
                            employee.getEName(), // Make sure you include the name here!
                            month.getValue(),
                            year,
                            dailyResponses
                    );
                })
                .collect(Collectors.toList()); // This returns List<AttendanceSummaryResponse>
    }
*/
  @Override
  public List<AttendanceSummaryResponse> getMyAttendanceByYear(String email, int year) {
      LocalDate today = LocalDate.now();

      // 1. Basic Year Validation
      if (year > today.getYear()) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Future year attendance cannot be viewed");
      }

      // 2. Determine how many months to show
      // If it's the current year, only go up to the current month. Otherwise, go to 12.
      int endMonth = (year == today.getYear()) ? today.getMonthValue() : 12;

      List<AttendanceSummaryResponse> yearlyList = new ArrayList<>();

      // 3. Loop through months and reuse your monthly method
      for (int month = 1; month <= endMonth; month++) {
          try {
              // We call the method you already wrote!
              AttendanceSummaryResponse monthlyData = getMyAttendanceByMonth(email, month, year);
              yearlyList.add(monthlyData);
          } catch (ResponseStatusException e) {
              // If a month is before joining date, your monthly method throws an exception.
              // We catch it here so the loop continues for the rest of the year.
              if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                  continue;
              }
              throw e;
          }
      }

      return yearlyList;
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
