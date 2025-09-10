package com.teste.HcodeR.messaging;

import com.teste.HcodeR.domain.event.BenefitEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BenefitEventsListener {
    @KafkaListener(
            topics = "benefit-events",
            groupId = "beneficios-consumer",
            containerFactory = "kafkaListenerContainerFactory" // usa a factory JSON do KafkaConfig
    )
    public void onMessage(BenefitEvent event) {
        log.info("🔥 Evento recebido do Kafka: type={} id={} name={} active={} at={}",
                event.getType(), event.getId(), event.getName(), event.getActive(), event.getOccurredAt());
        // aqui você pode auditar, notificar, indexar, etc.
    }
}
