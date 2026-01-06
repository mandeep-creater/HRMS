package com.hrms.Repo;

import com.hrms.Entity.Attendance;
import com.hrms.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long> {
    Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate now);
}
