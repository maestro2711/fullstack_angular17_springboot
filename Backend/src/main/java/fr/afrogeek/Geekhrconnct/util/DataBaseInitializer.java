package fr.afrogeek.Geekhrconnct.util;


import fr.afrogeek.Geekhrconnct.entity.Employee;
import fr.afrogeek.Geekhrconnct.enums.Position;
import fr.afrogeek.Geekhrconnct.enums.Gender;
import fr.afrogeek.Geekhrconnct.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
//@RequiredArgsConstructor
public class DataBaseInitializer implements CommandLineRunner {


    private final EmployeeRepository employeeRepository;

    public DataBaseInitializer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }




    @Override
    public void run(String... args) throws Exception {
        this.createEmployee();
        /*for(int i=0; i<50;i++){
            Position position = Position.values()[(i==0)?0: RANDOM.nextInt(Position.values().length-1)+1];
            //Position position = null;

            emplyeeService.createEmployee(employee);
        }*/
    }
    //@Transactional
    public void createEmployee() {
        // employeeRepository.deleteAll();
        List<Employee> employees = new ArrayList<>();
        // CEO
        Employee ceo = Employee.builder()
                .gender(Employee.Gender.WOMEN)
                .firstName("Matia")
                .lastName("Schmass")
                .email("matia.schmass@company.com")
                .phone("+49 170 1234567")
                .city("Berlin")
                .country("Germany")
                .dateOfBirth(LocalDateTime.of(1975, 5, 15, 0, 0))
                .remainingVacationDays(30L)
                .onVacation(false)
                .position(Position.CEO)
                //.imageURL("https://example.com/images/john_doe.jpg")
                .build();
        employees.add(ceo);

        // CTO
        Employee cto= Employee.builder()
                .gender(Employee.Gender.MEN)
                .firstName("Thomas")
                .lastName("Müller")
                .email("thomas.mueller@company.com")
                .phone("+49 171 2345678")
                .city("München")
                .country("Germany")
                .dateOfBirth(LocalDateTime.of(1980, 8, 22, 0, 0))
                .remainingVacationDays(28L)
                .onVacation(false)
                .position(Position.CTO)
                //.imageURL("https://example.com/images/jane_smith.jpg")
                .superior(ceo)
                .build();
        employees.add(cto);

        // COO
        Employee coo = Employee.builder()
                .gender(Employee.Gender.WOMEN)
                .firstName("Anna")
                .lastName("Weber")
                .email("anna.weber@company.com")
                .phone("+49 172 3456789")
                .city("Hamburg")
                .country("Germany")
                .dateOfBirth(LocalDateTime.of(1982, 3, 10, 0, 0))
                .remainingVacationDays(28L)
                .onVacation(false)
                .position(Position.COO)
                //.imageURL("https://example.com/images/michael_brown.jpg")
                .superior(ceo)
                .build();
        employees.add(coo);

        // TEAM_MANAGER_SOFTWARE
        Employee teamManager = Employee.builder()
                .gender(Employee.Gender.MEN)
                .firstName("Michael")
                .lastName("Fischer")
                .email("michael.fischer@company.com")
                .phone("+49 173 4567890")
                .city("Frankfurt")
                .country("Germany")
                .dateOfBirth(LocalDateTime.of(1985, 11, 5, 0, 0))
                .remainingVacationDays(26L)
                .onVacation(false)
                .position(Position.TEAM_MANAGER_SOFTWARE)
                //.imageURL("https://example.com/images/michael_brown.jpg")
                .superior(cto)
                .build();
        employees.add(teamManager);

        // SENIOR_SOFTWARE_DEVELOPER
        Employee seniorDev = Employee.builder()
                .gender(Employee.Gender.WOMEN)
                .firstName("Laura")
                .lastName("Becker")
                .email("laura.becker@company.com")
                .phone("+49 174 5678901")
                .city("Köln")
                .country("Germany")
                .dateOfBirth(LocalDateTime.of(1988, 7, 18, 0, 0))
                .remainingVacationDays(24L)
                .onVacation(false)
                .position(Position.SENIOR_SOFTWARE_DEVELOPER)
                .superior(teamManager)
                .build();
        employees.add(seniorDev);

        // SOFTWARE_DEVELOPER
        Employee dev = Employee.builder()
                .gender(Employee.Gender.MEN)
                .firstName("David")
                .lastName("Schneider")
                .email("david.schneider@company.com")
                .phone("+49 175 6789012")
                .city("Stuttgart")
                .country("Germany")
                .dateOfBirth(LocalDateTime.of(1990, 9, 30, 0, 0))
                .remainingVacationDays(22L)
                .onVacation(false)
                .position(Position.SOFTWARE_DEVELOPER)
                //.imageURL("https://example.com/images/sophia_garcia.jpg")
                .superior(seniorDev)
                .build();
        employees.add(dev);

        // JUNIOR_SOFTWARE_DEVELOPER
        Employee juniorDev = Employee.builder()
                .gender(Employee.Gender.WOMEN)
                .firstName("Sophie")
                .lastName("Wagner")
                .email("sophie.wagner@company.com")
                .phone("+49 176 7890123")
                .city("Düsseldorf")
                .country("Germany")
                .dateOfBirth(LocalDateTime.of(1995, 2, 14, 0, 0))
                .remainingVacationDays(20L)
                .onVacation(false)
                .position(Position.JUNIOR_SOFTWARE_DEVELOPER)
                //.imageURL("https://example.com/images/david_miller.jpg")
                .superior(dev)
                .build();
        employees.add(juniorDev);

        //Working Student

        Employee workingStudent = Employee.builder()
                .gender(Employee.Gender.MEN)
                .firstName("Sophan")
                .lastName("Wasah")
                .email("sophan.wasah@company.com")
                .phone("+49 176 7890789")
                .city("manheim")
                .country("Germany")
                .dateOfBirth(LocalDateTime.of(1997, 6, 13, 0, 0))
                .remainingVacationDays(26L)
                .onVacation(false)
                .position(Position.WORKING_STUDENT)
                //.imageURL("https://example.com/images/olivia_williams.jpg")
                .superior(juniorDev)
                .build();
        employees.add(workingStudent);
        employeeRepository.saveAll(Arrays.asList(ceo,cto,coo,teamManager,seniorDev,dev,juniorDev,workingStudent));

    }

}
