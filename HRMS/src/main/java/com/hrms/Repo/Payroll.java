package com.hrms.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Payroll extends JpaRepository<com.hrms.Entity.Payroll,Long> {
}
