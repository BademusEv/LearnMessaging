package com.pizza.ordersupplier.controller;

import com.pizza.ordersupplier.dto.OrderDto;
import com.pizza.ordersupplier.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService service;

  @PostMapping
  public HttpStatus makeAnOrder(@RequestBody OrderDto order){
    log.info("Made a new order: {}", order);
    service.addOrder(order);
    return HttpStatus.OK;
  }
}
