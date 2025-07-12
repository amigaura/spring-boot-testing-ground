package com.testing.ground.producer;

import com.testing.ground.dto.misc.EmailSendEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EmailProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEmailSendEvent(String topic, Object event) {
        try {
            if (!(event instanceof EmailSendEvent)) {
                LOGGER.error("Invalid event type: {}", event.getClass().getName());
                throw new IllegalArgumentException("Event must be of type EmailSendEvent");
            }
            EmailSendEvent emailEvent = (EmailSendEvent) event;
            kafkaTemplate.send("email-send-topic", String.valueOf(emailEvent.getRequestId()), event);
        } catch (Exception ex) {
            LOGGER.error("Kafka send failed", ex);
            throw new RuntimeException("Kafka producer error", ex);
        }
    }
}

