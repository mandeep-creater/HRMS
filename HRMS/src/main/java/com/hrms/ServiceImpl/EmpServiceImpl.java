package com.hrms.ServiceImpl;

import com.hrms.Entity.Employee;
import com.hrms.Mapper.EmployeeMapper;
import com.hrms.Repo.CompanyRepo;
import com.hrms.Repo.EmpRepo;
import com.hrms.ResponseDTO.EmployeeResponseDTO;
import com.hrms.enums.Role;
import com.hrms.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImpl implements UserDetailsService {

    @Autowired
    private EmpRepo empRepo;

    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return empRepo.findByEEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }

    public Page <EmployeeResponseDTO>getAllActiveEmpOfParticularCompa(String email , int page , int size, Status estatus) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   //    String hrCC  = (String) auth.getDetails();
       // System.out.println("Auth class = " + CCC);
        if (page < 0 || size <= 0 || size > 100) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid pagination parameters"
            );
        }
        Employee e = empRepo.findByEEmail(email).orElseThrow(
                ()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User Associate with company Email not exist: "+email
                )
        );
        String companyCode= e.getCompany().getCompanyCode();
        System.out.println("CompanyName "+companyCode);

        PageRequest pageable = PageRequest.of(page, size, Sort.by("eName").ascending());
        Page<Employee> employeePage;
        employeePage =empRepo.findByCompany_CompanyCodeAndEstatus( companyCode,estatus,pageable);
        return employeePage.map(employeeMapper::toEmpDto);
    }

    public String DeactivateEmp(String email,Status status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String hrCC = (String) auth.getDetails();

       Employee usr = empRepo.findByEEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,
               "Not able to find the Emp deatils using email Id: "
       ));
       String useCC = usr.getCompanyCode();
       String msg;
       if(!hrCC.equals(useCC)){
          msg = "Company code not matched";
       }

           usr.setEstatus(status);
           empRepo.save(usr);
            msg ="Operation Perform Sucessfully ";

       return msg;
    }

    public List<EmployeeResponseDTO> getAllHr(Role role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String CompanyCode = (String) auth.getDetails();

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        JwtService jwt = (JwtService) auth.getPrincipal();
//        String companyCode = jwt.//getClaim("companyCode");
        if(CompanyCode.isEmpty()){
            throw   new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Company code is empty");
        }
        List<Employee> hrlist = empRepo.findByCompany_CompanyCodeAndRole(CompanyCode,role);
        return hrlist.stream().map(employeeMapper::toEmpDto).collect(Collectors.toList());
    }
}
