package com.hrms.Repo;

import com.hrms.Entity.Company;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer> {

    boolean existsByCompanyEmail(String companyEmail);
    @Query("SELECT c FROM Company c WHERE (:active IS NULL OR c.is_active = :active)")
    Page<Company> findByIs_active(@Param("active") Boolean active, PageRequest pageable);
//    @Modifying
//    @Transactional
//    @Query("UPDATE Company c SET c.is_active = :status WHERE c.companyEmail = :companyEmail")
//    int updateCompanyStatus(String companyEmail, Boolean status);

    Optional<Company> findByCompanyEmail(String companyEmail);
}
