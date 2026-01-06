package com.hrms.ServiceImpl;

import com.hrms.Entity.Attendance;
import com.hrms.Entity.CompanyLocation;
import com.hrms.Entity.Employee;
import com.hrms.Mapper.AttendanceMapper;
import com.hrms.Repo.AttendanceRepo;
import com.hrms.Repo.CompanyLocationsRepo;
import com.hrms.Repo.EmpRepo;
import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import com.hrms.Service.AttendanceService;
import com.hrms.enums.AttendanceStatus;
import com.hrms.enums.WorkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private EmpRepo empRepo;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private AttendanceRepo attendanceRepo;

//    @Autowired
//    private CompanyLocation companyLocation;
//
//    @Autowired
//    private CompanyLocationsRepo companyLocationsRepo;



    @Override
    public AttendanceResponseDTO punchIn(AttendanceRequestDTO attendanceRequestDTO) {
        //1. Taken email
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        // 2. Employee fetch kiya;
        Optional<Employee> emp = empRepo.findByEEmail(email);
        Employee employee = emp.get();

       // System.out.println("Emplye daata :: "+employee.getEId()+" "+employee.getCompanyCode());
// 3. Check: Kya aaj ki attendance pehle se lag chuki hai?
        Optional<Attendance> existingAttendance = attendanceRepo.findByEmployeeAndDate(employee, LocalDate.now());
        if (existingAttendance.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Attendance already marked for today!");
        }
        Attendance attendance =  attendanceMapper.toEntity(attendanceRequestDTO);

        attendance.setEmployee(employee);
        //attendance.setCompanyLocation(employee.getCom);
        attendance.setDate(LocalDate.now());
        attendance.setCheckIn(LocalDateTime.now());
        // 6. Status Logic (Optional: e.g., if checkIn > 9:30 AM then LATE)
        attendance.setStatus(AttendanceStatus.PRESENT);
//        CompanyLocation location = companyLocationsRepo.findById(attendanceRequestDTO.get)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid location"));
//        attendance.setCompanyLocation(location);
        // 7. Save and Return
       // Attendance savedAttendance = attendanceRepo.save(attendance);
        //user -> email se company use company id or usse kisse kiya hai yeh to nikl jayega na
        //attendance.setCompanyLocation(companyLocation.getLocationName());
        System.out.println("Attendace :: "+attendance.getAttendanceId()+" "+
                    attendance.getCheckIn()+" "+attendance.getDate()+" "+attendance.getCompanyLocation());
            return null;
    }

    @Override
    public AttendanceResponseDTO punchOut(AttendanceRequestDTO attendanceRequestDTO) {
        return null;
    }

    @Override
    public AttendanceResponseDTO getTodayAttendance() {
        return null;
    }
}
