package com.hrms.ServiceImpl;

import com.hrms.Entity.Company;
import com.hrms.Entity.Employee;
import com.hrms.Mapper.EmployeeMapper;
import com.hrms.Repo.CompanyRepo;
import com.hrms.RequestsDTO.EmployeeLoginDTO;
import com.hrms.RequestsDTO.EmployeeRequestDTO;
import com.hrms.RequestsDTO.PasswordChangeDTORequest;
import com.hrms.ResponseDTO.EmployeeResponseDTO;
import com.hrms.enums.Role;
import com.hrms.Exceptions.ExceptionHandler;
import com.hrms.Repo.EmpRepo;
import com.hrms.Response.AuthenticationResponse;
import com.hrms.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.security.Security;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl {
    @Autowired
    private EmpRepo empRepo;

    @Autowired
    private CompanyRepo companyRepository;

    @Autowired
    private  EmployeeMapper employeeMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public EmployeeResponseDTO createAdminBySuperAdmin(EmployeeRequestDTO employeeRequestDTO) {



        if (employeeRequestDTO.getCompanyId() == null) {
            ExceptionHandler.handleBadRequest();
        }

        Company company = companyRepository.findById(Math.toIntExact(employeeRequestDTO.getCompanyId()))
                    .orElseThrow(() ->
                            ExceptionHandler.companyNotFoundWithId(employeeRequestDTO.getCompanyId())
                    );

            Employee employee = employeeMapper.toEmployeeEntity(employeeRequestDTO);
        String rawPassword = PasswordGenerator.generate();
            employee.setEPassword(
                    passwordEncoder.encode(rawPassword)
            );
            employee.setRole(Role.valueOf("ADMIN"));
            employee.setEDisgnation("ADMIN");
            // join date ,
            employee.setCompany(company);
            employee.setCompanyCode(company.getCompanyCode());
            Employee savedEmployee = empRepo.save(employee);
            //return employeeMapper.toEmpDto(savedEmployee,rawPassword);
        EmployeeResponseDTO response = employeeMapper.toEmpDto(savedEmployee);
        response.setEPassword(rawPassword);

        return response;
    }



    public AuthenticationResponse authenticate(EmployeeLoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Employee emp = empRepo.findByEEmail(request.getEmail())
              .orElseThrow(() -> ExceptionHandler.notFound(Employee.class, request.getEmail()));
//
//
        String accessToken = jwtService.generateAccessToken(emp , emp.getCompanyCode());


        return new AuthenticationResponse(accessToken, "User login was successful");
    }

    public EmployeeResponseDTO createEmpByAdminOrHr(EmployeeRequestDTO employeeRequestDTO) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
     //   System.out.println("Loogedin Email "+email);
        Employee creatorCompany = empRepo.findByEEmail(email).orElseThrow(()->ExceptionHandler.companyNotFoundWithEmail(email));

       Company company1 = creatorCompany.getCompany();
        System.out.println("Company Creator vale ki "+company1.toString());


        Employee employee = employeeMapper.toEmployeeEntity(employeeRequestDTO);
        if(empRepo.findByEEmail(employee.getEmail()).isPresent()){
            return  null;
        }
        String rawPassword = PasswordGenerator.generate();
        employee.setEPassword(
                passwordEncoder.encode(rawPassword)
        );
    //  employee.setRole(employee.getRole());
//        employee.setEDisgnation(employee.getEDisgnation());
        // join date ,
        employee.setCompany(company1);
        employee.setCompanyCode(company1.getCompanyCode());
        employee.setEstatus(Status.valueOf("ACTIVE"));
        employee.setECreatedByHrId(creatorCompany.getEId());
        employee.setEJoinDate(new Date());
        employee.setCreatedAt(LocalDateTime.now());
        //departid
        Employee savedEmployee = empRepo.save(employee);
        //return employeeMapper.toEmpDto(savedEmployee,rawPassword);
        EmployeeResponseDTO response = employeeMapper.toEmpDto(savedEmployee);
        response.setEPassword(rawPassword);

        return response;

      // return  null;
    }

    public String changePassword(PasswordChangeDTORequest passwordChangeDTORequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee emp = empRepo.findByEEmail(email).orElseThrow(()->ExceptionHandler.companyNotFoundWithEmail(email));
        String dPassword = emp.getPassword();
        System.out.println("Password : -> "+dPassword);
        String oldPassword = passwordChangeDTORequest.getOldPassword();
        String newPassword = passwordChangeDTORequest.getNewPassword();
        if (!passwordEncoder.matches(oldPassword, dPassword)) {
            return "Old password does not match";
        }
        else if (passwordEncoder.matches(newPassword, dPassword)) {
            return "New password cannot be same as old password";
        }
        else {
            emp.setEPassword(passwordEncoder.encode(newPassword));
            empRepo.save(emp);
            return "Password changed successfully";
        }


    }



    public static class PasswordGenerator {

        private static final String CHARS =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%!";
        private static final int PASSWORD_LENGTH = 12;

        private static final SecureRandom random = new SecureRandom();

        public static String generate() {
            StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
            for (int i = 0; i < PASSWORD_LENGTH; i++) {
                password.append(CHARS.charAt(random.nextInt(CHARS.length())));
            }
            return password.toString();
        }
    }

}


