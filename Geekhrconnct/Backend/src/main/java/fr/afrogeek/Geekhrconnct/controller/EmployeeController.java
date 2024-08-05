package fr.afrogeek.Geekhrconnct.controller;


import fr.afrogeek.Geekhrconnct.dto.EmployeeRequest;
import fr.afrogeek.Geekhrconnct.dto.EmployeeResponse;
import fr.afrogeek.Geekhrconnct.entity.Employee;
import fr.afrogeek.Geekhrconnct.services.EmplyeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmplyeeService emplyeeService;

    public EmployeeController(EmplyeeService emplyeeService) {

        this.emplyeeService = emplyeeService;
    }
    // abruf eines Mitarbeiter mit der ID
    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable UUID id) {
        return emplyeeService.getEmployeeResponseById(id);
    }

    //Abruf alle mitarbeitern imm system
    @GetMapping
    public List<EmployeeResponse> getAllEmployee(){
        return emplyeeService.getAllEmployee();
    }


    // neue Mitarbeiter erstellen
    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return emplyeeService.createEmployee(employeeRequest);
    }


    // ein Mitarbeiter aktualisieren
    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable UUID id, @RequestBody EmployeeRequest employeeRequest) {
        return emplyeeService.updateEmployee(id, employeeRequest);
    }

    // ein mitarbeieter löschen

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable UUID id){
        emplyeeService.deleteEmployee(id);
    }

}
