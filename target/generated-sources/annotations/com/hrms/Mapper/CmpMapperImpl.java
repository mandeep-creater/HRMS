package com.hrms.Mapper;

import com.hrms.DTO.CompanyDTO;
import com.hrms.Entity.Company;
import com.hrms.RequestsDTO.CompanyRequestDTO;
import com.hrms.ResponseDTO.CompanyResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-15T20:43:11+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CmpMapperImpl implements CmpMapper {

    @Override
    public Company toEntity(CompanyDTO companyDTO) {
        if ( companyDTO == null ) {
            return null;
        }

        Company company = new Company();

        if ( companyDTO.getcId() != null ) {
            company.setcId( companyDTO.getcId() );
        }
        company.setCompanyCode( companyDTO.getCompanyCode() );
        company.setCompanyName( companyDTO.getCompanyName() );
        company.setCompanyAddress( companyDTO.getCompanyAddress() );
        company.setCompanyPhone( companyDTO.getCompanyPhone() );
        company.setCompanyEmail( companyDTO.getCompanyEmail() );

        return company;
    }

    @Override
    public CompanyDTO toDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setcId( company.getcId() );
        companyDTO.setCompanyCode( company.getCompanyCode() );
        companyDTO.setCompanyName( company.getCompanyName() );
        companyDTO.setCompanyAddress( company.getCompanyAddress() );
        companyDTO.setCompanyPhone( company.getCompanyPhone() );
        companyDTO.setCompanyEmail( company.getCompanyEmail() );
        companyDTO.setCreatedAt( company.getCreatedAt() );
        companyDTO.setUpdatedAt( company.getUpdatedAt() );

        return companyDTO;
    }

    @Override
    public Company toRequestintoEntity(CompanyRequestDTO companyRequestDTO) {
        if ( companyRequestDTO == null ) {
            return null;
        }

        Company company = new Company();

        company.setCompanyName( companyRequestDTO.getCompanyName() );
        company.setCompanyAddress( companyRequestDTO.getCompanyAddress() );
        company.setCompanyPhone( companyRequestDTO.getCompanyPhone() );
        company.setCompanyEmail( companyRequestDTO.getCompanyEmail() );

        return company;
    }

    @Override
    public CompanyResponseDTO toCompanyResponseDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

        companyResponseDTO.setCreatedAt( company.getCreatedAt() );
        companyResponseDTO.setUpdatedAt( company.getUpdatedAt() );
        companyResponseDTO.setcId( company.getcId() );
        companyResponseDTO.setCompanyCode( company.getCompanyCode() );
        companyResponseDTO.setCompanyName( company.getCompanyName() );
        companyResponseDTO.setCompanyAddress( company.getCompanyAddress() );
        companyResponseDTO.setCompanyPhone( company.getCompanyPhone() );
        companyResponseDTO.setCompanyEmail( company.getCompanyEmail() );
        companyResponseDTO.setIs_active( company.isIs_active() );

        return companyResponseDTO;
    }
}
