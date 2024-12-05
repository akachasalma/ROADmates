package com.demand_service.demand_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemandProducer {

    private final KafkaTemplate<String, DemandConfirmation> kafkaTemplate;

    public void sendDemandConfirmation(DemandConfirmation demandConfirmation) {
        log.info("Sending demand confirmation: {}", demandConfirmation);
        Message<DemandConfirmation> message = MessageBuilder
                .withPayload(demandConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "demand-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
