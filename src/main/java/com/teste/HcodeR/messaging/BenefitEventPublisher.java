package com.teste.HcodeR.messaging;

import com.teste.HcodeR.config.KafkaConfig;
import com.teste.HcodeR.domain.Beneficio;
import com.teste.HcodeR.domain.event.BenefitEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BenefitEventPublisher {
    private final KafkaTemplate<String, BenefitEvent> kafka;

    public void publish(String type, Beneficio b) {
        BenefitEvent event = BenefitEvent.builder()
                .id(b.getId())
                .type(type)
                .name(b.getNome())
                .active(b.isActive())
                .occurredAt(Instant.now())
                .build();

        kafka.send(KafkaConfig.BENEFIT_TOPIC, String.valueOf(b.getId()), event);
    }
}
