package ar.lucas.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SuperheroesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperheroesApplication.class, args);
    }

}
