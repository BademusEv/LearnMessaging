package com.pizza.kitchen.service;

import com.pizza.kitchen.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PizzaMakerService {

    @SneakyThrows
    public void makePizza(OrderDto order){
      String pizza = order.getDishName() + " " + order.getSpecialPreferences();
      log.info("Making {} pizza", pizza);
      Thread.sleep(1000);
      log.info("Pizza {} is ready", pizza);
    }
}
