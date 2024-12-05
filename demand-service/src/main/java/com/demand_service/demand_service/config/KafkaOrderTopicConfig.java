package com.demand_service.demand_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic demandTopic(){
        return TopicBuilder
                .name("demand-topic")
                .build();
    }
}
