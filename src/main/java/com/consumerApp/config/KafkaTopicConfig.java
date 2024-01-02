package com.consumerApp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    //Beans that automatically create the Topics
    @Bean
    public NewTopic sessionTopic(){ return TopicBuilder.name("${session.topic.name}").build();}
    @Bean
    public NewTopic eventTopic(){
        return TopicBuilder.name("${event.topic.name}").build();
    }
}
