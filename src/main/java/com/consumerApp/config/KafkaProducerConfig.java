package com.consumerApp.config;

import com.consumerApp.entity.dto.EventInputDto;
import com.consumerApp.entity.dto.SessionInputDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> producerConfig() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configs;
    }

    @Bean
    public ProducerFactory<String,SessionInputDto> sessionProducerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, SessionInputDto> sessionKafkaTemplate(
            ProducerFactory<String,SessionInputDto> sessionProducerFactory
    ) {
        return new KafkaTemplate<>(sessionProducerFactory);
    }

    @Bean
    public ProducerFactory<String, EventInputDto> eventProducerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, EventInputDto> eventKafkaTemplate(
            ProducerFactory<String,EventInputDto> eventProducerFactory
    ) {
        return new KafkaTemplate<>(eventProducerFactory);
    }
}
