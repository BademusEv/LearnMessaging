package com.pizza.ordersupplier.service;

import com.pizza.ordersupplier.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private static final ActiveMQTopic PIZZA_ORDER_TOPIC = new ActiveMQTopic("pizza-orders");
  private final JmsTemplate jmsTemplate;

  public void addOrder(OrderDto order){
    jmsTemplate.convertAndSend(PIZZA_ORDER_TOPIC, order);
  }
}
