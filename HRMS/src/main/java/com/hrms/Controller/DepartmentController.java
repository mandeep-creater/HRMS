package com.hrms.Controller;

import com.hrms.RequestsDTO.DepartmentRequestDTO;
import com.hrms.ResponseDTO.ApiResponse;
import com.hrms.ResponseDTO.DepartmentResponseDTO;
import com.hrms.ResponseDTO.EmployeeResponseDTO;
import com.hrms.ResponseDTO.EmployeeResponseForDepartmentDTO;
import com.hrms.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

//    createDepartment() – Add new department
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>>createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO){
       DepartmentResponseDTO res= departmentService.createDepartmet(departmentRequestDTO);
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(new ApiResponse<>(200,true,res,"Department is Created Successfully"));
    }
//    updateDepartment() – Modify department details
@PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>>updateDepartment(@PathVariable Long id ,@RequestBody DepartmentRequestDTO departmentRequestDTO){
        DepartmentResponseDTO res= departmentService.updateDepartment(departmentRequestDTO,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,true,res,"Department is Updated Successfully"));

    }


//    deleteDepartment() – Remove department
@PutMapping("/{id}/deactivate")
public ResponseEntity<ApiResponse<String>> deactivateDepartment(@PathVariable Long id) {
    String res = departmentService.deleteDepartment(id, false);
    return ResponseEntity.ok(
            new ApiResponse<>(200, true, res, "Department deactivated successfully")
    );
}

    @PutMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<String>> activateDepartment(@PathVariable Long id) {
        String res = departmentService.deleteDepartment(id, true);
        return ResponseEntity.ok(
                new ApiResponse<>(200, true, res, "Department activated successfully")
        );
    }

//    viewDepartments() – List company departments
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDTO>>>viewDepartments(){
        List<DepartmentResponseDTO> res = departmentService.GetAllDepartment();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,true,res,"All the Departments retrive successfully"));
    }

    //Get ALL emp of Particular Department
@GetMapping("/{departmentId}/employees")
    public ResponseEntity<ApiResponse<List<EmployeeResponseForDepartmentDTO>>>getEmpByDept( @PathVariable Long departmentId){
        List<EmployeeResponseForDepartmentDTO> res = departmentService.getEmpByDept(departmentId);
    return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse<>(200,true,res,"Retrieve Emp successfully"));
}

}
