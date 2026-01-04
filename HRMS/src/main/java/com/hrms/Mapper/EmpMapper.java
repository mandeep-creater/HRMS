package com.hrms.Mapper;

import com.hrms.Entity.Employee;
import com.hrms.Response.EmpRegistrationRequest;
import com.hrms.Response.EmpRegistrationResponse;


public class EmpMapper {
    public static Employee toEntity(EmpRegistrationRequest dto) {
        Employee emp = new Employee();
       emp.setEName(dto.getName());
        emp.setEmail(dto.getEmail());

        return emp;
    }

    public static EmpRegistrationResponse toResponse(Employee emp) {
     EmpRegistrationResponse response = new EmpRegistrationResponse();


        response.setName(emp.getEName());
        response.setEmail(emp.getEmail());
        return response;
    }



}
