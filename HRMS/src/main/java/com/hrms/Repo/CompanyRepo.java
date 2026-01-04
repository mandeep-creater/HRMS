package com.hrms.Repo;

import com.hrms.Entity.Company;
import com.hrms.ResponseDTO.CompanyDropdownDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer> {

    boolean existsByCompanyEmail(String companyEmail);
    @Query("SELECT c FROM Company c WHERE (:active IS NULL OR c.is_active = :active)")
    Page<Company> findByIs_active(@Param("active") Boolean active, PageRequest pageable);
//    @Modifying
//    @Transactional
//    @Query("UPDATE CompanyController c SET c.is_active = :status WHERE c.companyEmail = :companyEmail")
//    int updateCompanyStatus(String companyEmail, Boolean status);

    Optional<Company> findByCompanyEmail(String companyEmail);

//    @Procedure(name = "Company.getActiveCompanies")
@Query(value = "CALL get_active_companies()", nativeQuery = true)
    List<Company> getActiveCompanies();

    Company findByCompanyCode(String companyCode);

    Company findByCId(int cid);
}
