package com.hrms.Service;

import com.hrms.RequestsDTO.CompanyRequestDTO;
import com.hrms.ResponseDTO.CompanyResponseDTO;
import org.springframework.data.domain.Page;

public interface CompanyService {
    CompanyResponseDTO createCompany(CompanyRequestDTO companyDTO);

    Page<CompanyResponseDTO> viewAllCompanies(Boolean active, int page, int size);
    CompanyResponseDTO changeCompanyStatus(String companyEmail, Boolean status);
}
