package com.hrms.Controller;

import com.hrms.RequestsDTO.CompanyLocationRequestDTO;
import com.hrms.RequestsDTO.CompanyRequestDTO;
import com.hrms.ResponseDTO.ApiResponse;
import com.hrms.ResponseDTO.CompanyDropdownDTO;
import com.hrms.ResponseDTO.CompanyLocationResponseDTO;
import com.hrms.ResponseDTO.CompanyResponseDTO;
import com.hrms.Service.CompanyLocationService;
import com.hrms.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyLocationService companyLocationService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CompanyResponseDTO>>createCompany(
            @RequestBody CompanyRequestDTO requestDTO) {
try{
        CompanyResponseDTO response = companyService.createCompany(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, true, response,"CompanyController is Created"));
    }catch (ResponseStatusException ex){
    return ResponseEntity.status(ex.getStatusCode())
            .body(new ApiResponse<>(ex.getStatusCode().value(), false,null, ex.getReason()));
    }
    }

    //update company --> pending
//    @PutMapping("/update")
//    public ResponseEntity<ApiResponse<CompanyResponseDTO>>updateCompany(
//            @RequestBody CompanyRequestDTO requestDTO) {
//        CompanyResponseDTO response = companyService.createCompany(requestDTO);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new ApiResponse<>(201, true, response,"Company  is Updated Successfully"));
//
//    }

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

@GetMapping("/dropdown")
    public ResponseEntity<ApiResponse<List<CompanyDropdownDTO>>> getCompanyDropdown() {

    try {
        List<CompanyDropdownDTO> list = companyService.getCompanyDropdown();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<List<CompanyDropdownDTO>>(200, true, list, "Company dropdown fetched"));
    } catch (ResponseStatusException ex) {
     return    ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>(ex.getStatusCode().value(), false, null, "Data is not present"));
    }

   }

    @PatchMapping("/status-update/{email}")
    public ResponseEntity<ApiResponse<CompanyResponseDTO>> changeCompanyStatus(
            @PathVariable("email") String companyEmail,
    @RequestParam boolean active) {
       try {
            companyService.changeCompanyStatus(companyEmail, active);

            String message = active
                    ? "CompanyController activated successfully"
                    : "CompanyController deactivated successfully";

            CompanyResponseDTO companyResponseDTO =companyService.changeCompanyStatus(companyEmail, active);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(200, true, companyResponseDTO, message));

        }catch (ResponseStatusException ex) {
           return ResponseEntity.status(ex.getStatusCode())
                   .body(new ApiResponse<>(ex.getStatusCode().value(), false, null, ex.getReason()));
       }
    }

    //Create a new location
    @PostMapping("/create-location")
    public ResponseEntity<ApiResponse<CompanyLocationResponseDTO>>createLocation(@RequestBody CompanyLocationRequestDTO companyLocationRequestDTO){

      CompanyLocationResponseDTO res=companyLocationService.createLocation(companyLocationRequestDTO);
      return  ResponseEntity.status(HttpStatus.CREATED)
              .body(new ApiResponse<>(201,true,res,"Company Location Saved in Db Successfully"));
    }


    //Update an existing location
    @PutMapping("/update-location/{id}")
    public ResponseEntity<ApiResponse<CompanyLocationResponseDTO>> updateLocation(
            @PathVariable Long id,
            @RequestBody CompanyLocationRequestDTO companyLocationRequestDTO)
    {
        return null;
    }


    //Get a location by ID
    @GetMapping("/get-location/{id}")
    public ResponseEntity<ApiResponse<CompanyLocationResponseDTO>> getLocationById(@PathVariable Long id) {

        CompanyLocationResponseDTO res = companyLocationService.getLocationById(id);
        return  ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse<>(200,true,res,"Company Location Fetch Successfully"));
    }


    //  Get all locations for a company
    @GetMapping("/get-locations-by-company/{companyId}")
    public ResponseEntity<ApiResponse<List<CompanyLocationResponseDTO>>> getLocationsByCompany(@PathVariable Long companyId) {
        List<CompanyLocationResponseDTO> res = companyLocationService.getLocationsByCompany(companyId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,true,res,"List of Company different location fetch successfully"));
    }

    // Delete a location
    @PutMapping("/soft-delete-location/{id}")
    public ResponseEntity<ApiResponse<String>> deleteLocation(@PathVariable Long id,@RequestParam boolean active) {
       String res= companyLocationService.deleteLocation(id , active);

        return  ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,true,res,"on location activity"));
    }

    }
