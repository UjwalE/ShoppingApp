package com.springboot.shoppingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.springboot.shoppingapp.repo")
@EntityScan("com.springboot.shoppingapp.entity")
@SpringBootApplication
public class ShoppingappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingappApplication.class, args);
    }

}
