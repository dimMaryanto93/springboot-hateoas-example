package com.maryanto.dimas.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class SpringHateoasExample {

    public static void main(String[] args) {
        SpringApplication.run(SpringHateoasExample.class, args);
    }
}
