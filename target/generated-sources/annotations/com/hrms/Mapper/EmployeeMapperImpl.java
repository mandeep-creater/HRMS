package com.hrms.Mapper;

import com.hrms.Entity.Company;
import com.hrms.Entity.Department;
import com.hrms.Entity.Employee;
import com.hrms.RequestsDTO.EmployeeRequestDTO;
import com.hrms.ResponseDTO.EmployeeResponseDTO;
import com.hrms.ResponseDTO.EmployeeResponseForDepartmentDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T12:36:14+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEmployeeEntity(EmployeeRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEName( dto.getEName() );
        employee.setEmail( dto.getEmail() );
        employee.setEPassword( dto.getEPassword() );
        employee.setEDisgnation( dto.getEDisgnation() );
        employee.setESalary( dto.getESalary() );
        employee.setEJoinDate( dto.getEJoinDate() );
        employee.setEManagerId( dto.getEManagerId() );
        employee.setECreatedByHrId( dto.getECreatedByHrId() );
        employee.setRole( dto.getRole() );
        employee.setEPhone( dto.getEPhone() );
        employee.setEstatus( dto.getEstatus() );
        employee.setEGender( dto.getEGender() );

        return employee;
    }

    @Override
    public EmployeeResponseDTO toEmpDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();

        Integer cId = employeeCompanyCId( employee );
        if ( cId != null ) {
            employeeResponseDTO.setCompanyId( cId.longValue() );
        }
        employeeResponseDTO.setCompanyCode( employeeCompanyCompanyCode( employee ) );
        employeeResponseDTO.setEId( employee.getEId() );
        employeeResponseDTO.setEName( employee.getEName() );
        employeeResponseDTO.setEmail( employee.getEmail() );
        employeeResponseDTO.setEDisgnation( employee.getEDisgnation() );
        employeeResponseDTO.setESalary( employee.getESalary() );
        employeeResponseDTO.setEJoinDate( employee.getEJoinDate() );
        employeeResponseDTO.setEManagerId( employee.getEManagerId() );
        employeeResponseDTO.setECreatedByHrId( employee.getECreatedByHrId() );
        employeeResponseDTO.setEPhone( employee.getEPhone() );
        employeeResponseDTO.setEGender( employee.getEGender() );
        employeeResponseDTO.setEstatus( employee.getEstatus() );
        employeeResponseDTO.setRole( employee.getRole() );

        return employeeResponseDTO;
    }

    @Override
    public EmployeeResponseForDepartmentDTO toEmpDeptRes(Employee e) {
        if ( e == null ) {
            return null;
        }

        EmployeeResponseForDepartmentDTO employeeResponseForDepartmentDTO = new EmployeeResponseForDepartmentDTO();

        employeeResponseForDepartmentDTO.setCompanyCode( employeeCompanyCompanyCode( e ) );
        employeeResponseForDepartmentDTO.setDepartmentId( eDepartmentId( e ) );
        Integer cId = employeeCompanyCId( e );
        if ( cId != null ) {
            employeeResponseForDepartmentDTO.setCompanyId( cId.longValue() );
        }
        employeeResponseForDepartmentDTO.setEId( e.getEId() );
        employeeResponseForDepartmentDTO.setEName( e.getEName() );
        employeeResponseForDepartmentDTO.setEmail( e.getEmail() );
        employeeResponseForDepartmentDTO.setEDisgnation( e.getEDisgnation() );
        employeeResponseForDepartmentDTO.setEJoinDate( e.getEJoinDate() );
        employeeResponseForDepartmentDTO.setEManagerId( e.getEManagerId() );
        employeeResponseForDepartmentDTO.setECreatedByHrId( e.getECreatedByHrId() );
        employeeResponseForDepartmentDTO.setEPhone( e.getEPhone() );
        employeeResponseForDepartmentDTO.setEGender( e.getEGender() );
        employeeResponseForDepartmentDTO.setEstatus( e.getEstatus() );
        employeeResponseForDepartmentDTO.setRole( e.getRole() );

        return employeeResponseForDepartmentDTO;
    }

    private Integer employeeCompanyCId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Company company = employee.getCompany();
        if ( company == null ) {
            return null;
        }
        int cId = company.getcId();
        return cId;
    }

    private String employeeCompanyCompanyCode(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Company company = employee.getCompany();
        if ( company == null ) {
            return null;
        }
        String companyCode = company.getCompanyCode();
        if ( companyCode == null ) {
            return null;
        }
        return companyCode;
    }

    private Long eDepartmentId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        Long id = department.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
