package ru.raspad.marketspring;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MarketSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketSpringApplication.class, args);
    }

}
