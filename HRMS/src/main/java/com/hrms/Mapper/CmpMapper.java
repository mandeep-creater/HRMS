package com.hrms.Mapper;

import com.hrms.DTO.CompanyDTO;
import com.hrms.Entity.Company;
import com.hrms.RequestsDTO.CompanyRequestDTO;
import com.hrms.ResponseDTO.CompanyDropdownDTO;
import com.hrms.ResponseDTO.CompanyResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CmpMapper {
  //  CmpMapper cmpMapper = Mappers.getMapper(CmpMapper.class);
     Company toEntity(CompanyDTO companyDTO);
     CompanyDTO toDto(Company company);

     Company toRequestintoEntity(CompanyRequestDTO companyRequestDTO);

    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
     CompanyResponseDTO toCompanyResponseDto(Company company);

}
