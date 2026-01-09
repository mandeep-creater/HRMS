package com.hrms.Repo;

import com.hrms.Entity.Attendance;
import com.hrms.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long> {
    Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate now);

    List<Attendance> findByEmployeeAndDateBetween(Employee employee, LocalDate start, LocalDate end);
//    Attendance findByEmployee_EId(Long eId);
}
