package com.hrms.Mapper;

import com.hrms.Entity.Department;
import com.hrms.Entity.Employee;
import com.hrms.RequestsDTO.DepartmentRequestDTO;
import com.hrms.RequestsDTO.EmployeeRequestDTO;
import com.hrms.ResponseDTO.DepartmentResponseDTO;
import com.hrms.ResponseDTO.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    //// Request DTO → Entity
    @Mapping(target = "company.cId", source = "companyId")
    Department toDepartmentEntity(DepartmentRequestDTO dto);

    @Mapping(source = "company.cId", target = "companyId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    DepartmentResponseDTO toDepartmentDto(Department department);
}
