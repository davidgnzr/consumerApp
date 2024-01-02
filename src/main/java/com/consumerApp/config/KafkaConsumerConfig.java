package com.consumerApp.config;

import com.consumerApp.entity.dto.SessionInputDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> consumerConfig() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        // Enable dto packages for deserialization:
        configs.put(JsonDeserializer.TRUSTED_PACKAGES, "com.consumerApp.entity.dto");
        return configs;
    }

    @Bean
    public ConsumerFactory<String, SessionInputDto> sessionConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SessionInputDto> kafkaListenerContainerFactory(
            ConsumerFactory<String,SessionInputDto> sessionConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, SessionInputDto> consumerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        consumerFactory.setConsumerFactory(sessionConsumerFactory);
        return consumerFactory;
    }
}
