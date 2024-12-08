package fr.afrogeek.Geekhrconnct.services;

import fr.afrogeek.Geekhrconnct.entity.Employee;
import fr.afrogeek.Geekhrconnct.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class EmplyeeService {

    private final EmployeeRepository employeeRepository;

    public EmplyeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // pour creer un collaborateur
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    // en mitarbeiter abrufen
    public Employee getEmployeebyId(UUID id){
        return employeeRepository.findById(id).orElseThrow(
                ()->new RuntimeException("employee with id  "+id+ "not found")
        );
    }
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    //mitarbeiter informationen aktualisieren
    public Employee upDateEmployee(UUID id, Employee employeeDetails){
        Employee employee = this.getEmployeebyId(id);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setGender(employeeDetails.getGender());
        employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        employee.setCity(employeeDetails.getCity());
        employee.setCountry(employeeDetails.getCountry());
        employee.setRemainingVacationDays(employeeDetails.getRemainingVacationDays());
        employee.setOnVacation(employeeDetails.getOnVacation());
        employee.setPosition(employeeDetails.getPosition());
        return employeeRepository.save(employee);
    }
    //ein mitarbeiter löschen
    public void deleteEmployee(UUID id){
        employeeRepository.deleteById(id);

    }
}
