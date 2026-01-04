package com.hrms.Service;

import com.hrms.RequestsDTO.CompanyRequestDTO;
import com.hrms.ResponseDTO.CompanyDropdownDTO;
import com.hrms.ResponseDTO.CompanyResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    CompanyResponseDTO createCompany(CompanyRequestDTO companyDTO);

    Page<CompanyResponseDTO> viewAllCompanies(Boolean active, int page, int size);
    CompanyResponseDTO changeCompanyStatus(String companyEmail, Boolean status);

    List<CompanyDropdownDTO> getCompanyDropdown();
}
