package fr.afrogeek.Geekhrconnct.controller;


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
    public Employee getEmployeeById(@PathVariable UUID id){
        return emplyeeService.getEmployeebyId(id);
    }
    //Abruf alle mitarbeitern imm system
    @GetMapping
    public List<Employee> getAllEmployee(){
        return emplyeeService.getAllEmployee();
    }
    // neue Mitarbeiter erstellen
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return emplyeeService.createEmployee(employee);
    }
    // ein Mitarbeiter aktualisieren
    @PutMapping("/{id}")
    public Employee upDateEmployee(@PathVariable UUID id,@RequestBody Employee employee){
        return emplyeeService.upDateEmployee(id,employee);
    }
    // ein mitarbeieter löschen

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable UUID id){
        emplyeeService.deleteEmployee(id);
    }

}
