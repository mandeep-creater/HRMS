package com.hrms.Mapper;

import com.hrms.Entity.Company;
import com.hrms.Entity.CompanyLocation;
import com.hrms.RequestsDTO.CompanyLocationRequestDTO;
import com.hrms.ResponseDTO.CompanyLocationResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-04T19:17:36+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CompanyLocationsMapperImpl implements CompanyLocationsMapper {

    @Override
    public CompanyLocation toEntity(CompanyLocationRequestDTO companyLocationRequestDTO) {
        if ( companyLocationRequestDTO == null ) {
            return null;
        }

        CompanyLocation companyLocation = new CompanyLocation();

        companyLocation.setLocationName( companyLocationRequestDTO.getLocationName() );
        companyLocation.setPincode( companyLocationRequestDTO.getPincode() );
        companyLocation.setLatitude( companyLocationRequestDTO.getLatitude() );
        companyLocation.setLongitude( companyLocationRequestDTO.getLongitude() );
        companyLocation.setAllowedRadiusInMeters( companyLocationRequestDTO.getAllowedRadiusInMeters() );

        return companyLocation;
    }

    @Override
    public CompanyLocationResponseDTO toDto(CompanyLocation companyLocation) {
        if ( companyLocation == null ) {
            return null;
        }

        CompanyLocationResponseDTO companyLocationResponseDTO = new CompanyLocationResponseDTO();

        Integer cId = companyLocationCompanyCId( companyLocation );
        if ( cId != null ) {
            companyLocationResponseDTO.setCompanyId( cId.longValue() );
        }
        companyLocationResponseDTO.setCompanyCode( companyLocationCompanyCompanyCode( companyLocation ) );
        companyLocationResponseDTO.setCreatedAt( companyLocation.getCreatedAt() );
        companyLocationResponseDTO.setUpdatedAt( companyLocation.getUpdatedAt() );
        companyLocationResponseDTO.setId( companyLocation.getId() );
        companyLocationResponseDTO.setLocationName( companyLocation.getLocationName() );
        companyLocationResponseDTO.setPincode( companyLocation.getPincode() );
        companyLocationResponseDTO.setLatitude( companyLocation.getLatitude() );
        companyLocationResponseDTO.setLongitude( companyLocation.getLongitude() );
        companyLocationResponseDTO.setAllowedRadiusInMeters( companyLocation.getAllowedRadiusInMeters() );

        return companyLocationResponseDTO;
    }

    private Integer companyLocationCompanyCId(CompanyLocation companyLocation) {
        if ( companyLocation == null ) {
            return null;
        }
        Company company = companyLocation.getCompany();
        if ( company == null ) {
            return null;
        }
        int cId = company.getcId();
        return cId;
    }

    private String companyLocationCompanyCompanyCode(CompanyLocation companyLocation) {
        if ( companyLocation == null ) {
            return null;
        }
        Company company = companyLocation.getCompany();
        if ( company == null ) {
            return null;
        }
        String companyCode = company.getCompanyCode();
        if ( companyCode == null ) {
            return null;
        }
        return companyCode;
    }
}
