package com.hrms.Mapper;

import com.hrms.Entity.Employee;
import com.hrms.RequestsDTO.EmployeeRequestDTO;
import com.hrms.ResponseDTO.EmployeeResponseDTO;
import com.hrms.ResponseDTO.EmployeeResponseForDepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployeeEntity(EmployeeRequestDTO dto);

    @Mapping(source = "company.cId", target = "companyId")
    @Mapping(source = "company.companyCode", target = "companyCode")
    @Mapping(source = "companyLocation.id",target = "companyLocationId")
    @Mapping(source = "companyLocation.locationCode" , target = "companyLocationCode")
    @Mapping(source = "department.id", target = "departmentId")
    EmployeeResponseDTO toEmpDto(Employee employee);
    @Mapping(source = "company.companyCode", target = "companyCode")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "company.cId", target = "companyId")
    EmployeeResponseForDepartmentDTO toEmpDeptRes(Employee e);
}
