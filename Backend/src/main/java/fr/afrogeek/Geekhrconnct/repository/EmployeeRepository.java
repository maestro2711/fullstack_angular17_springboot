package fr.afrogeek.Geekhrconnct.repository;

import fr.afrogeek.Geekhrconnct.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,UUID> {
    // List<Employee> findByEmployeeId(UUID employeeid);
}
