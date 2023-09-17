package com.pizza.kitchen.jms;

import com.pizza.kitchen.dto.OrderDto;
import com.pizza.kitchen.service.PizzaMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderListener {

  private final PizzaMakerService service;

  @JmsListener(destination = "pizza-orders", containerFactory = "topicListenerContainerFactory")
  public void processOrder(OrderDto order) {
    service.makePizza(order);
  }

  @JmsListener(destination = "pizza-orders", containerFactory = "durableTopicListenerContainerFactory")
  public void orderHistory(OrderDto order) {
    log.info("Record of the order created: {}", order);
  }
}
