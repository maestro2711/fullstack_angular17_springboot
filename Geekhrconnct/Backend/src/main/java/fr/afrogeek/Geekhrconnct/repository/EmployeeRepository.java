package fr.afrogeek.Geekhrconnct.repository;

import fr.afrogeek.Geekhrconnct.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RepositoryRestResource(exported = false)
public interface EmployeeRepository  extends JpaRepository<Employee,UUID> {
    //List<Employee> findByLastName(String lastname);
}
