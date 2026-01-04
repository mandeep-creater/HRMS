package com.hrms.Repo;

import com.hrms.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {
    boolean existsByDepartmentNameAndCompany_CompanyCode(String departmentName, String companyCode);

    Department findByIdAndCompanyCode(Long id, String companyCode);

    List<Department> findByCompanyCode(String companyCode);

    boolean existsByDepartmentNameAndCompanyCodeAndIdNot(String departmentName, String companyCode, Long id);
}
