package ru.proitr.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.proitr.example"})
public class ExampleApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(ExampleApplication.class, args);
    }
}
