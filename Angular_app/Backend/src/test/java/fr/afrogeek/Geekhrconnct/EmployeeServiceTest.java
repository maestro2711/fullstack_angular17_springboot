package fr.afrogeek.Geekhrconnct;

import fr.afrogeek.Geekhrconnct.dto.EmployeeRequest;
import fr.afrogeek.Geekhrconnct.dto.EmployeeResponse;
import fr.afrogeek.Geekhrconnct.entity.Employee;
import fr.afrogeek.Geekhrconnct.enums.Position;
import fr.afrogeek.Geekhrconnct.repository.EmployeeRepository;
import fr.afrogeek.Geekhrconnct.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeRequest employeeRequest;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .id(UUID.randomUUID())
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
                .superior(null)
                //.imageURL("https://example.com/images/john_doe.jpg")
                .build();

        employeeRequest= EmployeeRequest.builder()
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
                .superiorId(null)
                //.imageURL("https://example.com/images/john_doe.jpg")
                .build();
    }

    @Test
    void testGetAllEmployee(){
        //Arrange
        when (employeeRepository.findAll()).thenReturn(List.of(employee));

        //Act
        List<EmployeeResponse> employees = employeeService.getAllEmployee();

        //Assert
        assertEquals(1,employees.size());
        assertEquals(employee.toResponse().toString(),employees.get(0).toString());

        verify(employeeRepository,times(1)).findAll();
    }

    @Test
    void testDeleteEmployeeById(){

        //Arrange
        UUID employeeId = UUID.randomUUID();

        //Act
        employeeService.deleteEmployeeById(employeeId);


        //Assert
        verify(employeeRepository,times(1)).deleteById(employeeId);
    }


    @Test
    void testCreateEmployee(){
        // Arrange
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        //Act

        EmployeeResponse employeeResponse  = employeeService.createEmployee(employeeRequest);

        //Assert
        assertNotNull(employeeResponse);
        assertEquals(employee.toResponse().toString(),employeeResponse.toString());
        verify(employeeRepository,times(1)).save(any(Employee.class));
    }


    @Test
    void testUpDateEmployee(){

        //Arrange
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        //Act

        EmployeeResponse employeeResponse = employeeService.updateEmployee(employee.getId(),employeeRequest);

        //Assert
        assertNotNull(employeeResponse);
        assertEquals(employee.toResponse().toString(),employeeResponse.toString());
        verify(employeeRepository,times(1)).findById(employee.getId());
        verify(employeeRepository,times(1)).save(any(Employee.class));
    }

    @Test
    void testGetEmployeeById(){
        UUID employeeId = UUID.randomUUID();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));


        //Act
        Employee employeeResponse = employeeService.getEmployeeById(employeeId);

        //Assert

        assertNotNull(employeeResponse);

        verify(employeeRepository, times(1)).findById(employeeId);
    }
}
