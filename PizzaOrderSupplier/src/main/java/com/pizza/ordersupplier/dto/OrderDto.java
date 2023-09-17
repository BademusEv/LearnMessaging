package com.pizza.ordersupplier.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class OrderDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private String dishName;
  private String specialPreferences;
}
