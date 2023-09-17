package com.pizza.ordersupplier.dto;

import lombok.Data;

@Data
public class PizzaStateDto {
  private final String state;
  private final OrderDto order;
}
