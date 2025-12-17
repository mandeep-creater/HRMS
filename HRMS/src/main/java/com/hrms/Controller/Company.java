package com.hrms.Controller;

import com.hrms.RequestsDTO.CompanyRequestDTO;
import com.hrms.ResponseDTO.ApiResponse;
import com.hrms.ResponseDTO.CompanyResponseDTO;
import com.hrms.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/company")
public class Company {
    @Autowired
    private CompanyService companyService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CompanyResponseDTO>>createCompany(
            @RequestBody CompanyRequestDTO requestDTO) {
try{
        CompanyResponseDTO response = companyService.createCompany(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, true, response,"Company is Created"));
    }catch (ResponseStatusException ex){
    return ResponseEntity.status(ex.getStatusCode())
            .body(new ApiResponse<>(ex.getStatusCode().value(), false,null, ex.getReason()));
    }
    }

@GetMapping("/viewAll")
    public ResponseEntity<ApiResponse<Page<CompanyResponseDTO>>>GetAllCompanies(@RequestParam(required = false,name = "is_active") Boolean active,@RequestParam(defaultValue = "0") int page,  @RequestParam(defaultValue = "5") int size) {
    try {

        Page<CompanyResponseDTO> response = companyService.viewAllCompanies(active, page, size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, true, response, "List fetched successfully"));

    } catch (ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(new ApiResponse<>(ex.getStatusCode().value(), false, null, ex.getReason()));
    }
}
    @PatchMapping("/status-update/{email}")
    public ResponseEntity<ApiResponse<CompanyResponseDTO>> changeCompanyStatus(
            @PathVariable("email") String companyEmail,
    @RequestParam boolean active) {
       try {
            companyService.changeCompanyStatus(companyEmail, active);

            String message = active
                    ? "Company activated successfully"
                    : "Company deactivated successfully";

            CompanyResponseDTO companyResponseDTO =companyService.changeCompanyStatus(companyEmail, active);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(200, true, companyResponseDTO, message));

        }catch (ResponseStatusException ex) {
           return ResponseEntity.status(ex.getStatusCode())
                   .body(new ApiResponse<>(ex.getStatusCode().value(), false, null, ex.getReason()));
       }
    }

}
