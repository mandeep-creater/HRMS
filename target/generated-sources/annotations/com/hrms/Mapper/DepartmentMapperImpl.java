package com.hrms.Mapper;

import com.hrms.Entity.Company;
import com.hrms.Entity.Department;
import com.hrms.RequestsDTO.DepartmentRequestDTO;
import com.hrms.ResponseDTO.DepartmentResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-01T13:25:17+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toDepartmentEntity(DepartmentRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Department department = new Department();

        department.setCompany( departmentRequestDTOToCompany( dto ) );
        department.setDepartmentName( dto.getDepartmentName() );
        department.setActive( dto.isActive() );

        return department;
    }

    @Override
    public DepartmentResponseDTO toDepartmentDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();

        Integer cId = departmentCompanyCId( department );
        if ( cId != null ) {
            departmentResponseDTO.setCompanyId( cId.longValue() );
        }
        departmentResponseDTO.setCreatedAt( department.getCreatedAt() );
        departmentResponseDTO.setUpdatedAt( department.getUpdatedAt() );
        departmentResponseDTO.setId( department.getId() );
        departmentResponseDTO.setDepartmentName( department.getDepartmentName() );
        departmentResponseDTO.setActive( department.isActive() );
        departmentResponseDTO.setCompanyCode( department.getCompanyCode() );

        return departmentResponseDTO;
    }

    protected Company departmentRequestDTOToCompany(DepartmentRequestDTO departmentRequestDTO) {
        if ( departmentRequestDTO == null ) {
            return null;
        }

        Company company = new Company();

        if ( departmentRequestDTO.getCompanyId() != null ) {
            company.setcId( departmentRequestDTO.getCompanyId().intValue() );
        }

        return company;
    }

    private Integer departmentCompanyCId(Department department) {
        if ( department == null ) {
            return null;
        }
        Company company = department.getCompany();
        if ( company == null ) {
            return null;
        }
        int cId = company.getcId();
        return cId;
    }
}
