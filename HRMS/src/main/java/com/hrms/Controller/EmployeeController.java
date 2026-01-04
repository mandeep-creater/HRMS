package com.hrms.Controller;

import com.hrms.RequestsDTO.EmployeeLoginDTO;
import com.hrms.RequestsDTO.EmployeeRequestDTO;
import com.hrms.RequestsDTO.EmployeeStatusRequest;
import com.hrms.RequestsDTO.PasswordChangeDTORequest;
import com.hrms.Response.AuthenticationResponse;
import com.hrms.ResponseDTO.ApiResponse;
import com.hrms.ResponseDTO.EmployeeResponseDTO;
import com.hrms.ServiceImpl.AuthenticationServiceImpl;
import com.hrms.ServiceImpl.EmpServiceImpl;
import com.hrms.enums.Role;
import com.hrms.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private  AuthenticationServiceImpl authenticationService;

    @Autowired
    private EmpServiceImpl empServiceImpl;

//    createUser() – Create any company user (ADMIN / HR / EMPLOYEE)


   @PostMapping("/create-admin")  // Create Admin
   public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createAdminBySuperAdmin(@RequestBody EmployeeRequestDTO employeeRequestDTO){
    //   String role = principal.getName();
       try {
           EmployeeResponseDTO employee = authenticationService.createAdminBySuperAdmin(employeeRequestDTO);

           return ResponseEntity.status(HttpStatus.CREATED)
                   .body(new ApiResponse<>(201, true, employee, "Employee is Created"));
       }catch (Exception ex){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ApiResponse<>(500,false,null,"Something went Wrong"));
       }
   }
    @PostMapping("/create-emp")             //Create EMP
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createEmpByAdminOrHr(@RequestBody EmployeeRequestDTO employeeRequestDTO){
     EmployeeResponseDTO employee = authenticationService.createEmpByAdminOrHr(employeeRequestDTO);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(
                            409,
                            false,
                            null,
                            "Employee already exists with email"
                    ));
        }
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(new ApiResponse<>(201,true,employee,"Employee is Created"));

   }



    @PostMapping("/login")            //Login
    public  ResponseEntity<ApiResponse<AuthenticationResponse>> loginByEmp(@RequestBody EmployeeLoginDTO request){
        AuthenticationResponse response = authenticationService.authenticate(request);
        return  ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,true,response,"Employee Login Successfully"));
    }


    @PostMapping("/change-password")       //Update Password
    public ResponseEntity<ApiResponse<String>>changePassword(@RequestBody PasswordChangeDTORequest passwordChangeDTORequest){
       String msg = authenticationService.changePassword(passwordChangeDTORequest);
       return ResponseEntity.status(HttpStatus.OK)
               .body(new ApiResponse<>(200,true,msg,"PasswordReq"));
    }

//    updateUser() – Update user details



//    activateUser() – Activate user account
@GetMapping("/get-All")
    public ResponseEntity<ApiResponse<Page<EmployeeResponseDTO>>>GetAllActiveEmp(Principal principal, @RequestParam(defaultValue = "ACTIVE") Status status, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
       String  email= principal.getName();
        Page<EmployeeResponseDTO> res = empServiceImpl.getAllActiveEmpOfParticularCompa(email,page ,size,status);
       return ResponseEntity.status(HttpStatus.OK)
               .body(new ApiResponse<>(200,true,res,"Get All "+status+" Emp"));
    }

//    deactivateUser() – Deactivate user account

    @PutMapping("/update-status")
    public ResponseEntity<ApiResponse<String>>DeActiveUser(@RequestBody EmployeeStatusRequest employeeStatusRequest){
       String res = empServiceImpl.DeactivateEmp(employeeStatusRequest.getEmail(),employeeStatusRequest.getStatus());
       return ResponseEntity.status(HttpStatus.OK)
               .body(new ApiResponse<>(200,true,res,"Operation on Emp status"));
    }


//    assignRole() – Assign or change user role
//    public  ResponseEntity<ApiResponse<String>>ChangeEmpRole(@RequestBody EmpRoleChangeRequest empRoleChangeRequest){
//
//    }


    //Get All HR
    @GetMapping("/all-hr")
    public  ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getAllHR(){
       List<EmployeeResponseDTO>res= empServiceImpl.getAllHr(Role.valueOf("HR"));
       return  ResponseEntity.status(HttpStatus.OK)
               .body(new ApiResponse<>(200,true,res,"All the HR List"));
    }

    //GetAllADMIN

    @GetMapping("/all-admin")
    public  ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getAllAdmin(){
        List<EmployeeResponseDTO>res= empServiceImpl.getAllHr(Role.valueOf("ADMIN"));
        return  ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,true,res,"All the Admin List"));
    }

    //GetALLMANAGER

//    @GetMapping("/all-manager")
//    public  ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getAllManager(){
//        List<EmployeeResponseDTO>res= empServiceImpl.getAllHr(Role.valueOf("MANAGER"));
//        return  ResponseEntity.status(HttpStatus.OK)
//                .body(new ApiResponse<>(200,true,res,"All the Manager List"));
//    }
}
