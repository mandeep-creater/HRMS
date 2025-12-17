package com.hrms.Repo;

import com.hrms.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpRepo extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEEmail(String eEmail);
}
