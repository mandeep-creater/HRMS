package com.hrms.Controller;


import com.hrms.RequestsDTO.AttendanceRequestDTO;
import com.hrms.ResponseDTO.ApiResponse;
import com.hrms.ResponseDTO.AttendanceResponseDTO;
import com.hrms.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/today")
    public ResponseEntity<ApiResponse<AttendanceResponseDTO>>getTodayAttendance(){
        AttendanceResponseDTO res = attendanceService.getTodayAttendance();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,true,res, "Today Attendance retrive Sucessfully !"));
    }

}
