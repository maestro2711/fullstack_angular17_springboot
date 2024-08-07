package fr.afrogeek.Geekhrconnct.services;

import fr.afrogeek.Geekhrconnct.dto.EmployeeRequest;
import fr.afrogeek.Geekhrconnct.dto.EmployeeResponse;
import fr.afrogeek.Geekhrconnct.entity.Employee;
import fr.afrogeek.Geekhrconnct.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service

public class EmplyeeService {

    private final EmployeeRepository employeeRepository;

    public EmplyeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    // pour creer un collaborateur
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {

        return employeeRepository.save(this.employeeRequestToEmployee(employeeRequest)).toResponse();
    }

    // en mitarbeiter abrufen
    /*public Employee getEmployeebyId(UUID id){
        return employeeRepository.findById(id).orElseThrow(
                ()->new RuntimeException("employee with id  "+id+ "not found")
        );
    }*/
    public List<EmployeeResponse> getAllEmployee() {
        return employeeRepository.findAll().stream().map(Employee::toResponse).toList();
    }

    //mitarbeiter informationen aktualisieren
    public EmployeeResponse updateEmployee(UUID id, EmployeeRequest employeeRequest) {
        Employee employeeToUpdate = getEmployeeById(id);

        employeeToUpdate.setGender(employeeRequest.getGender());
        employeeToUpdate.setFirstName(employeeRequest.getFirstName());
        employeeToUpdate.setLastName(employeeRequest.getLastName());
        employeeToUpdate.setEmail(employeeRequest.getEmail());
        employeeToUpdate.setPhone(employeeRequest.getPhone());
        employeeToUpdate.setDateOfBirth(employeeRequest.getDateOfBirth());
        employeeToUpdate.setCity(employeeRequest.getCity());
        employeeToUpdate.setCountry(employeeRequest.getCountry());
        employeeToUpdate.setRemainingVacationDays(employeeRequest.getRemainingVacationDays());
        employeeToUpdate.setOnVacation(employeeRequest.getOnVacation());
        employeeToUpdate.setPosition(employeeRequest.getPosition());
        //employeeToUpdate.setImageURL(employeeRequest.getImageURL());
        employeeToUpdate.setSuperior(this.getSuperiorById(employeeRequest.getSuperiorId()));

        return employeeRepository.save(employeeToUpdate).toResponse();
    }


    public EmployeeResponse getEmployeeResponseById(UUID id) {
        return this.getEmployeeById(id).toResponse();
    }

    private Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Employee with "+id+" not found")
        );
    }

    //ein mitarbeiter löschen
    public void deleteEmployee(UUID id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Mitarbeiter mit ID " + id + " nicht gefunden");
        }
    }
    // ein superior durch die Id finden
    private Employee getSuperiorById(UUID id) {
        Employee superior = null;
        if(id !=null){
            superior = this.getEmployeeById(id);
        }
        return superior;
    }

    private Employee employeeRequestToEmployee(EmployeeRequest employeeRequest){
        Employee superior = this.getSuperiorById(employeeRequest.getSuperiorId());
        return Employee.builder()
                .gender(employeeRequest.getGender())
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .phone(employeeRequest.getPhone())
                .dateOfBirth(employeeRequest.getDateOfBirth())
                .city(employeeRequest.getCity())
                .country(employeeRequest.getCountry())
                .remainingVacationDays(employeeRequest.getRemainingVacationDays())
                .onVacation(employeeRequest.isOnVacation())
                .position(employeeRequest.getPosition())
                //.imageURL(employeeRequest.getImageURL())
                .superior(superior)
                .build();
    }
}