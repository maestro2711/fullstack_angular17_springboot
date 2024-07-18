package fr.afrogeek.Geekhrconnct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GeekhrconnctApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeekhrconnctApplication.class, args);

	}
	@GetMapping("/")
	public String home(){
		return "willkoommen zum Springboot Projekt";
	}

}
