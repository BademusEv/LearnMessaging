package com.pizza.ordersupplier.service;


import com.pizza.ordersupplier.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  public static final String VIRTUAL_TOPIC_PIZZA_ORDER = "VirtualTopic.PizzaOrder";
  private final JmsTemplate jmsTemplate;

  @SneakyThrows
  public void addOrder(OrderDto order) {
    jmsTemplate.convertAndSend(new ActiveMQTopic(VIRTUAL_TOPIC_PIZZA_ORDER), order);
    log.info("Order sent to a kitchen");
  }

}
