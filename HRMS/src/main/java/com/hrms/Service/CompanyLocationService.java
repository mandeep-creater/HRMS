package com.hrms.Service;

import com.hrms.RequestsDTO.CompanyLocationRequestDTO;
import com.hrms.ResponseDTO.CompanyLocationResponseDTO;

import java.util.List;

public interface CompanyLocationService {
    CompanyLocationResponseDTO createLocation(CompanyLocationRequestDTO requestDTO);

    CompanyLocationResponseDTO updateLocation(Long locationId, CompanyLocationRequestDTO requestDTO);
    CompanyLocationResponseDTO getLocationById(Long locationId);
    List<CompanyLocationResponseDTO> getLocationsByCompany(Long companyId);
    String deleteLocation(Long locationId ,boolean active);

}
