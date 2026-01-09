package com.hrms.Controller;


import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.*;
import com.hrms.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/punch-in")
    public ResponseEntity<ApiResponse<AttendanceResponseDTO>>punchIn(@RequestBody AttendanceRequestDTO attendanceRequestDTO){
        AttendanceResponseDTO res= attendanceService.punchIn(attendanceRequestDTO);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201,true,res,"Punch-In Successfully"));
    }


    @PostMapping("/punch-out")
    public ResponseEntity<ApiResponse<AttendanceResponseDTO>>punchOut(@RequestBody AttendanceRequestDTO attendanceRequestDTO){
        AttendanceResponseDTO res= attendanceService.punchOut(attendanceRequestDTO);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201,true,res,"Punch-Out Successfully"));
    }


    @GetMapping("/me/today")
    public ResponseEntity<ApiResponse<AttendanceDayResponse>>getTodayAttendance( Principal principal){
        String email = principal.getName();

        AttendanceDayResponse res = attendanceService.getTodayAttendance(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,true,res, "Today Attendance retrive Sucessfully !"));
    }

    @GetMapping("/me/month")
    public ResponseEntity<ApiResponse<AttendanceSummaryResponse>>getAttendanceByMonth(Principal principal,@RequestParam("month") int month ,  @RequestParam int year ){
    String email = principal.getName();
    AttendanceSummaryResponse res= attendanceService.getMyAttendanceByMonth(email,month , year);
  //      AttendanceDayResponse
        return  ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,true,res,"Attendance Fetch Successfully By Month!"));
    }
    @GetMapping("/me/year")
    public ResponseEntity<ApiResponse<AttendanceSummaryResponse>> getMyAttendanceByYear(
            @RequestParam int year){
        return  null;
    }

    @GetMapping("/company/today")
    public ResponseEntity<ApiResponse<List<AttendanceTodayResponse>>> getTodayAttendanceByCompany(
            @RequestParam String companyCode){
        return  null;
    }

    @GetMapping("/company/month")
    public ResponseEntity<ApiResponse<List<AttendanceSummaryResponse>>> getAttendanceByMonthForCompany(
            @RequestParam String companyCode,
            @RequestParam int month,
            @RequestParam int year){
        return null;
    }

    @GetMapping("/company/year")
    public ResponseEntity<ApiResponse<List<AttendanceSummaryResponse>>> getAttendanceByYearForCompany(
            @RequestParam String companyCode,
            @RequestParam int year){
        return null;
    }

}
