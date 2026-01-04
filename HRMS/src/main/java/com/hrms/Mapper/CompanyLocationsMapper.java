package com.hrms.Mapper;

import com.hrms.Entity.CompanyLocation;
import com.hrms.RequestsDTO.CompanyLocationRequestDTO;
import com.hrms.ResponseDTO.CompanyLocationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyLocationsMapper {

    @Mapping(target = "company",ignore = true)
    CompanyLocation toEntity(CompanyLocationRequestDTO companyLocationRequestDTO);
    @Mapping(source = "company.cId", target = "companyId")
    @Mapping(source = "company.companyCode", target = "companyCode")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    CompanyLocationResponseDTO toDto(CompanyLocation companyLocation);
}
