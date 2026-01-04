package com.hrms.Repo;

import com.hrms.Entity.Employee;
import com.hrms.enums.Role;
import com.hrms.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpRepo extends JpaRepository<Employee,Long> {
    @Query( value = "CALL sp_find_employee_by_email(:email)",
            nativeQuery = true
    )
Optional<Employee> findByEEmail(@Param("email") String email);


    Page<Employee> findByCompany_CompanyCodeAndEstatus(String companyCode, Status estatus,
                                                       Pageable pageable);

    List<Employee> findByCompany_CompanyCodeAndRole(String companyCode , Role role);

    List<Employee> findByDepartmentIdAndCompanyCode(Long departmentId, String companyCode);
}
