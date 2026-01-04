package com.hrms.ServiceImpl;

import com.hrms.Entity.Company;
import com.hrms.Exceptions.ExceptionHandler;
import com.hrms.Mapper.CmpMapper;
import com.hrms.Repo.CompanyRepo;
import com.hrms.RequestsDTO.CompanyRequestDTO;
import com.hrms.ResponseDTO.CompanyDropdownDTO;
import com.hrms.ResponseDTO.CompanyResponseDTO;
import com.hrms.Service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CmpMapper companyMapper;

    @Autowired
    private CompanyRepo companyRepo;




    @Override
    public CompanyResponseDTO createCompany(CompanyRequestDTO companyDTO) {
//1.Business validation
        if(companyRepo.existsByCompanyEmail(companyDTO.getCompanyEmail())){
            throw ExceptionHandler.companyAlreadyExists(
                    companyDTO.getCompanyEmail()
            );
        }

        //2.DTO->Entity
        Company entity = companyMapper.toRequestintoEntity(companyDTO);
     //   entity.setCreated_at(LocalDateTime.now());
     //   entity.setUpdated_at(LocalDateTime.now());
//3 persist
        Company savedCompany = companyRepo.save(entity);


        //setting  company code
       if(savedCompany.getCompanyName() !=null
               && savedCompany.getCompanyName().length()>=3){
           String prefix = savedCompany.getCompanyName().substring(0, 3).toUpperCase();

           savedCompany.setCompanyCode(prefix+savedCompany.getcId());
        savedCompany=companyRepo.save(savedCompany);
       }

//4. entity -> dto
        return companyMapper.toCompanyResponseDto(savedCompany);
    }


    @Override
    public Page<CompanyResponseDTO> viewAllCompanies(Boolean active, int page, int size) {
        long start = System.currentTimeMillis();
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Company>companyPage;
        if(active == null){
           companyPage = companyRepo.findAll(pageable);
       }else{
            companyPage =companyRepo.findByIs_active(active, pageable);
        }
        long end = System.currentTimeMillis();
        System.out.println("Execution time: {} ms"+ (end - start));
        return companyPage.map(companyMapper::toCompanyResponseDto);
    }

    public CompanyResponseDTO changeCompanyStatus(String companyEmail, Boolean status) {
        Company company = companyRepo.findByCompanyEmail(companyEmail)
        .orElseThrow(()->ExceptionHandler
                .companyNotFoundWithEmail(companyEmail));

        company.setIs_active(status);


        // save not strictly required if entity is managed, but safe
        Company updatedCompany = companyRepo.save(company);

        return companyMapper.toCompanyResponseDto(updatedCompany);

    }

    @Override
    public List<CompanyDropdownDTO> getCompanyDropdown() {
        List<Company> db = companyRepo.getActiveCompanies();
        return db.stream()
                .map(c -> new CompanyDropdownDTO( c.getcId(),c.getCompanyName()))
                .toList();
    }

}
