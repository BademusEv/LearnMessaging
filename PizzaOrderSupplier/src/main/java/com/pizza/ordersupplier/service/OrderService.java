package com.pizza.ordersupplier.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizza.ordersupplier.dto.OrderDto;
import com.pizza.ordersupplier.dto.PizzaStateDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  private static final String PIZZA_ORDER_QUEUE = "pizza-order-queue";
  private final JmsTemplate jmsTemplate;
  private final MessageConverter messageConverter;
  private final ObjectMapper objectMapper;

  @SneakyThrows
  public void addOrder(OrderDto order) {
    ActiveMQTextMessage message = (ActiveMQTextMessage) jmsTemplate.sendAndReceive(
        PIZZA_ORDER_QUEUE, session -> messageConverter.toMessage(order, session));
    assert message != null;

    PizzaStateDto pizzaStateDto = objectMapper.readValue(message.getText(), PizzaStateDto.class);
    log.info("Pizza {} {} is {}", pizzaStateDto.getOrder().getDishName(),
        pizzaStateDto.getOrder().getSpecialPreferences(), pizzaStateDto.getState());
  }

}
