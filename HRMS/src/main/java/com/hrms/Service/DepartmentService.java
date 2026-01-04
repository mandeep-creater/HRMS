package com.hrms.Service;

import com.hrms.RequestsDTO.DepartmentRequestDTO;
import com.hrms.ResponseDTO.DepartmentResponseDTO;
import com.hrms.ResponseDTO.EmployeeResponseForDepartmentDTO;

import java.util.List;

public interface DepartmentService {
    //create

    DepartmentResponseDTO createDepartmet(DepartmentRequestDTO departmentRequestDTO );


    //view

    List<DepartmentResponseDTO> GetAllDepartment();


    //updateDepartment
    DepartmentResponseDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO,Long id);


    //Delete
    String deleteDepartment(Long id, boolean active);

    List<EmployeeResponseForDepartmentDTO> getEmpByDept(Long departmentId);
}
