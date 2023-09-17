package com.pizza.kitchen.configuration;

import com.pizza.kitchen.dto.OrderDto;
import com.pizza.kitchen.dto.PizzaStateDto;
import java.util.Map;
import javax.jms.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@RequiredArgsConstructor
public class JmsConfiguration {

  public static final Map<String, Class<?>> typeIdMappings = Map.of(
      "com.pizza.ordersupplier.dto.OrderDto", OrderDto.class
  );

  @Bean
  public MessageConverter messageConverter(){
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    converter.setTypeIdMappings(typeIdMappings);
    return converter;
  }

  @Bean
  public DefaultJmsListenerContainerFactory queueListenerContainerFactory(ConnectionFactory connectionFactory){
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter());
    return factory;
  }
}
