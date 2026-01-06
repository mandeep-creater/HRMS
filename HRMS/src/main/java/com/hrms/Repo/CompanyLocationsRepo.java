package com.hrms.Repo;

import com.hrms.Entity.Company;
import com.hrms.Entity.CompanyLocation;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Repository
public interface CompanyLocationsRepo extends JpaRepository<CompanyLocation,Long>{

    List<CompanyLocation> findByCompanyCId(int intExact);

    boolean existsByCompanyAndLocationShortCode(Company company, String shortCode);
}