package com.hrms.ServiceImpl;

import com.hrms.Entity.Company;
import com.hrms.Entity.CompanyLocation;
import com.hrms.Exceptions.ExceptionHandler;
import com.hrms.Mapper.CompanyLocationsMapper;
import com.hrms.Repo.CompanyLocationsRepo;
import com.hrms.Repo.CompanyRepo;
import com.hrms.RequestsDTO.CompanyLocationRequestDTO;
import com.hrms.ResponseDTO.CompanyLocationResponseDTO;
import com.hrms.Service.CompanyLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyLocationServiceImpl implements CompanyLocationService {

    @Autowired
    private CompanyLocationsMapper companyLocationsMapper;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private CompanyLocationsRepo companyLocationsRepo;
    @Override
    public CompanyLocationResponseDTO createLocation(CompanyLocationRequestDTO requestDTO) {
        System.out.println("🔥 Request Received: Lat=" + requestDTO.getLatitude() + " Lon=" + requestDTO.getLongitude());

        // 1. Validation
        if(requestDTO.getCompanyId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company ID is missing in the request!");
        }

        // 2. Find Company
        // Seedha Math.toIntExact(requestDTO.getCompanyId()) use karein, long cid ki extra line ki zarurat nahi
        Company company = companyRepo.findByCId(Math.toIntExact(requestDTO.getCompanyId()));

        if (company == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found for the given ID");
        }

        // 3. Map DTO to Entity
        // Make sure Mapper mein @Mapping(target = "company", ignore = true) laga ho
        CompanyLocation cl = companyLocationsMapper.toEntity(requestDTO);

        // 4. Manual Link (This is mandatory for the Foreign Key)
        cl.setCompany(company);

        //5.for softdelet
       // cl.setIs_active(true);
        // 6. Save to DB
        CompanyLocation savedCompanyLocation = companyLocationsRepo.save(cl);

        System.out.println(" Saved Successfully: ID " + savedCompanyLocation.getId());

        // 6. Return Response
        return companyLocationsMapper.toDto(savedCompanyLocation);
    }

    @Override
    public CompanyLocationResponseDTO updateLocation(Long locationId, CompanyLocationRequestDTO requestDTO) {
        return null;
    }

    @Override
    public CompanyLocationResponseDTO getLocationById(Long locationId) {

        // 1. Database se fetch karein
        Optional<CompanyLocation> companyLocation = companyLocationsRepo.findById(locationId);

        // 2. Check karein ki data mila ya nahi
        if (companyLocation.isEmpty()) {
            System.out.println(" Location not found for ID: " + locationId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found with ID: " + locationId);
        }

        // 3. Optional se Entity nikalien
        CompanyLocation cl = companyLocation.get();

        System.out.println(" Found Location: " + cl.getLocationName());

        // 4. Entity ko ResponseDTO mein map karke return karein
        return companyLocationsMapper.toDto(cl);
    }

    @Override
    public List<CompanyLocationResponseDTO> getLocationsByCompany(Long companyId) {
        List<CompanyLocation> locations = companyLocationsRepo.findByCompanyCId(Math.toIntExact(companyId));
        if (locations.isEmpty()) {
            System.out.println("⚠ No locations found for Company ID: " + companyId);
            return Collections.emptyList(); // Ya throw 404
        }
        return locations.stream().map(companyLocationsMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public String deleteLocation(Long locationId , boolean active) {

        Optional<CompanyLocation> companyLocation=companyLocationsRepo.findById(locationId);
        if(companyLocation.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Company is not available at this Location ID : "+locationId);
        }
        if(companyLocation.get().getIs_active() == active){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Location is already " + (active ? "ACTIVE" : "INACTIVE"));
        }
        CompanyLocation loc = companyLocation.get();
        loc.setIs_active(active);
        companyLocationsRepo.save(loc);

        return "Operation Done Successfully";
    }
}
