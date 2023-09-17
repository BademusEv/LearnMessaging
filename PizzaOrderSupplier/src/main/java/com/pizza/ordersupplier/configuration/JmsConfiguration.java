package com.pizza.ordersupplier.configuration;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.TemporaryTopic;
import javax.jms.Topic;
import org.apache.activemq.broker.region.TempTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfiguration {

  @Bean
  public MessageConverter jacksonMessageConverter(){
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }

  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setConnectionFactory(connectionFactory);
    jmsTemplate.setMessageConverter(jacksonMessageConverter());
    jmsTemplate.setPubSubDomain(true);
    return jmsTemplate;
  }

}
