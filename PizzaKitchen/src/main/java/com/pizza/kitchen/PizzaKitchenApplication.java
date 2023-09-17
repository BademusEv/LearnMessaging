package com.pizza.kitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class PizzaKitchenApplication {

  public static void main(String[] args) {
    SpringApplication.run(PizzaKitchenApplication.class, args);
  }

}
