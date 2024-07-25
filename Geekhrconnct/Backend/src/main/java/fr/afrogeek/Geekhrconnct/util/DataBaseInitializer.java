package fr.afrogeek.Geekhrconnct.util;


import fr.afrogeek.Geekhrconnct.entity.Employee;
import fr.afrogeek.Geekhrconnct.enums.Position;
import fr.afrogeek.Geekhrconnct.repository.EmployeeRepository;
import fr.afrogeek.Geekhrconnct.services.EmplyeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
//@RequiredArgsConstructor
public class DataBaseInitializer implements CommandLineRunner {


    public DataBaseInitializer(EmplyeeService emplyeeService) {
        this.emplyeeService = emplyeeService;
    }

    private final EmplyeeService emplyeeService ;
    private static final Random RANDOM = new Random();


    @Override
    public void run(String... args) throws Exception {
        for(int i=0; i<50;i++){
            Position position = Position.values()[(i==0)?0: RANDOM.nextInt(Position.values().length-1)+1];
            //Position position = null;
            Employee employee = generateEmployee(position  ,i);
            emplyeeService.createEmployee(employee);
        }
    }
    private Employee generateEmployee(Position position, int index){
        String[] firstNames = {
                "Njoya","Mballa","Ngando","Ewane","Bikoi","Efouda","Dikoume","kenmoe","Yves","Joel",
                "Akon","Beyala","Zang","Muna","Etoudi","Nkono","Mbouh","Eto","Matip","Marc","Sandro",
                "Kameni","Choupo","Aboubakar","Bassogog","Djemba","song","Makoun","Njitap","kwekeu","Marco",
                "Webo","Kana","Mandjeck","Sali","Nguemo","Oyongo","Ndy","Zambo","Alias","alioum","Metomo",
                "Ateba","Edgard","Meyong","Marou","Bahoken","Tawamba","Lucy","Allianz"
        };
        String[] lastNames ={
                "Okafor", "Mensah", "Diallo", "Nkosi", "Abebe", "Muthomi", "Kone", "Adekunle", "Osei", "Mamba",
                "Oka", "Mens", "Dial", "kosi", "Abe", "Mutho", "Kon", "Adekune", "Osi", "Mambali",
                "Osafore", "ensoh", "ilal", "Nosien", "bebe", "Mutusomi", "Konate", "Abdullah", "Oite", "amaba",
                "Wokafor", "Gensah", "Tiallo", "Rosi", "Batabe", "Sathomi", "Paone", "LAdekunle", "Qosei", "Bamba",
                "Okafir", "Mensih", "Diallu", "Nkosui", "Abebela", "Mutmi", "Konsah", "Adekunlin", "Oseneh", "Mamina"
        };
        String[] cities = {"Lagos", "Accra", "Nairobi", "Johannesburg", "Cairo", "Addis Ababa", "Dakar", "Kinshasa", "Khartoum", "Casablanca"};
        String[] countries = {"Nigeria", "Ghana", "Kenya", "South Africa", "Egypt", "Ethiopia", "Senegal", "DR Congo", "Sudan", "Morocco"};
        Employee.Gender[] genders={Employee.Gender.MEN, Employee.Gender.WOMEN};
        String firstName = firstNames[index];
        String lastName = lastNames[index];
        String email = firstName.toLowerCase() + "." +lastName.toLowerCase()
+ "@gmail.com";
        String phone= "2376" + (1000000 + RANDOM.nextInt(9000000));


        // Generiere ein zufälliges Geburtsdatum für Mitarbeiter zwischen 18 und 65 Jahren
       /* LocalDateTime now = LocalDateTime.now();
        long minDay = now.minusYears(65).toEpochSecond(ZoneOffset.UTC);
        long maxDay = now.minusYears(18).toEpochSecond(ZoneOffset.UTC);
        Random random = null;
        long randomDay = minDay + (long) (random.nextDouble() * (maxDay - minDay));*/
        LocalDateTime dateOfBirth = LocalDateTime.of(1970 + RANDOM.nextInt(50), 1 + RANDOM.nextInt(12),1 + RANDOM.nextInt(28),0,0 );

        String city = cities[RANDOM.nextInt(cities.length)];
        String country = countries[RANDOM.nextInt(countries.length)];
        Employee.Gender gender = genders[RANDOM.nextInt(genders.length)];
        Long  remainingVacationDays = 5 + RANDOM.nextLong(25);
        boolean onVacation = RANDOM.nextBoolean();

        return new Employee(UUID.randomUUID(), gender,firstName, lastName, email, phone, city,country,dateOfBirth,remainingVacationDays,onVacation,position);

    }
}
