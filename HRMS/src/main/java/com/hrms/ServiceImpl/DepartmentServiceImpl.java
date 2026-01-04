package com.hrms.ServiceImpl;

import com.hrms.Entity.Company;
import com.hrms.Entity.Department;
import com.hrms.Entity.Employee;
import com.hrms.Exceptions.ExceptionHandler;
import com.hrms.Mapper.DepartmentMapper;
import com.hrms.Mapper.EmployeeMapper;
import com.hrms.Repo.CompanyRepo;
import com.hrms.Repo.DepartmentRepo;
import com.hrms.Repo.EmpRepo;
import com.hrms.RequestsDTO.DepartmentRequestDTO;
import com.hrms.ResponseDTO.DepartmentResponseDTO;
import com.hrms.ResponseDTO.EmployeeResponseForDepartmentDTO;
import com.hrms.Service.DepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
   private DepartmentRepo departmentRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public DepartmentResponseDTO createDepartmet(DepartmentRequestDTO departmentRequestDTO) {
       Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getDetails() == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Unauthorized access");
        }
        String companyCode = (String) auth.getDetails();

        Company company = companyRepo.findByCompanyCode(companyCode);
      //  System.out.println("Company :: "+company.toString());
        if(company== null){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Company Code");
        }

        boolean exists = departmentRepo.existsByDepartmentNameAndCompany_CompanyCode(
                departmentRequestDTO.getDepartmentName(),
                companyCode
        );

        if (exists) {
//            throw  new ResponseStatusException(
//                    HttpStatus.CONFLICT,
//                    "Department name already exists");
         throw new  ExceptionHandler. DuplicateDepartmentException(
                    "Department name already exists in this company");

        }
        Department department = departmentMapper.toDepartmentEntity(departmentRequestDTO);
        department.setCompanyCode(companyCode);
        department.setCompany(company);
        department.setActive(true);
//       DepartmentResponseDTO dto = departmentMapper.toDepartmentDto(department);
//        System.out.println("Department ka Demo Data :: "+dto.getDepartmentName()+" "
//        +dto.getCompanyCode()+" "+dto.getCompanyId()+" "+dto.getCreatedAt());

       Department savedDepartment = departmentRepo.save(department);

       return departmentMapper.toDepartmentDto(savedDepartment);

    }

    @Override
    public List<DepartmentResponseDTO> GetAllDepartment() {
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getDetails() == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Unauthorized access");
        }
        String companyCode = (String) auth.getDetails();
        List<Department> departments = departmentRepo.findByCompanyCode(companyCode);
        if(departments==null){
            throw  new ResponseStatusException(HttpStatus.NO_CONTENT,"Empty");
        }

        return departments.stream().map(departmentMapper::toDepartmentDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO,Long id) {
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getDetails() == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Unauthorized access");
        }
        String companyCode = (String) auth.getDetails();

        Company company = companyRepo.findByCompanyCode(companyCode);
        //  System.out.println("Company :: "+company.toString());
        if(company== null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Company Code");
        }

        Department department = departmentRepo.findByIdAndCompanyCode(id,companyCode);
        if(department == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Department not Found with this Id : "+id);
        }

        boolean duplicateExists =
                departmentRepo.existsByDepartmentNameAndCompanyCodeAndIdNot(
                        departmentRequestDTO.getDepartmentName(),
                        companyCode,
                        id
                );

        if (duplicateExists) {
            throw new ExceptionHandler.DuplicateDepartmentException(
                    "Department name already exists in this company"
            );
        }
        department.setDepartmentName(
                departmentRequestDTO.getDepartmentName()
        );
        department.setActive(true);
        Department updatedDepartment = departmentRepo.save(department);

        return departmentMapper.toDepartmentDto(updatedDepartment);
    }

    @Override
    public String deleteDepartment(Long id,boolean active) {
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getDetails() == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Unauthorized access");
        }
        String companyCode = (String) auth.getDetails();
        Department dept = departmentRepo.findByIdAndCompanyCode(id,companyCode);
        if(dept == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Department not Found with this Id : "+id);
        }
        if (dept.isActive() == active) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    active
                            ? "Department is already active"
                            : "Department is already inactive"
            );
        }
       dept.setActive(active);
        departmentRepo.save(dept);

        return active
                ? "Department activated successfully"
                : "Department deactivated successfully";
    }

    @Override
    public List<EmployeeResponseForDepartmentDTO> getEmpByDept(Long departmentId) {
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getDetails() == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Unauthorized access");
        }
        String companyCode = (String) auth.getDetails();
        Department department = departmentRepo
                .findByIdAndCompanyCode(departmentId, companyCode);
        if (department == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Department not found with id : " + departmentId
            );
        }

        List<Employee>empByDepart = empRepo.findByDepartmentIdAndCompanyCode(
                departmentId, companyCode
        );
        return empByDepart.stream().map(employeeMapper::toEmpDeptRes).collect(Collectors.toList());
    }
}
