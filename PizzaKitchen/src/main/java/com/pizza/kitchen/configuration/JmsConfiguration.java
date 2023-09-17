package com.pizza.kitchen.configuration;

import com.pizza.kitchen.dto.OrderDto;
import java.util.Map;
import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfiguration {
  @Bean
  public MessageConverter messageConverter(){
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    converter.setTypeIdMappings(Map.of("com.pizza.ordersupplier.dto.OrderDto", OrderDto.class));
    return converter;
  }

  @Bean
  public DefaultJmsListenerContainerFactory topicListenerContainerFactory(ConnectionFactory connectionFactory){
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter());
    factory.setPubSubDomain(true);
    return factory;
  }

  @Bean
  public DefaultJmsListenerContainerFactory durableTopicListenerContainerFactory(ConnectionFactory connectionFactory){
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter());
    factory.setPubSubDomain(true);
    factory.setSubscriptionDurable(true);
    factory.setClientId("412d162d-1348-42fe-a55e-310219c701e3");
    return factory;
  }
}
