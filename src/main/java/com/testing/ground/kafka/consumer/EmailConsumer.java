package com.testing.ground.kafka.consumer;

import com.testing.ground.dto.misc.EmailSendEvent;
import com.testing.ground.entity.misc.EmailRequest;
import com.testing.ground.repository.misc.EmailRequestRepository;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailConsumer {
    
    Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

    private final EmailRequestRepository requestRepo;
    private final JavaMailSender mailSender;

    public EmailConsumer(EmailRequestRepository requestRepo, JavaMailSender mailSender) {
        this.requestRepo = requestRepo;
        this.mailSender = mailSender;
    }

    @KafkaListener(topics = "${kafka.email-send-topic.topic-name}",
            containerFactory = "topicListenerContainerFactory",
            groupId = "${kafka.email-send-topic.consumer-group-id}")
    public void listen(EmailSendEvent event) {
        LOGGER.info("Received EmailSendEvent: {}", event);
        if (event == null || event.getRequestId() == null) {
            LOGGER.error("Invalid EmailSendEvent received: {}", event);
            return;
        }
        // Fetch the email request from the repository
        EmailRequest req = null;
        try {
            LOGGER.info("Processing email send event for request ID: {}", event.getRequestId());
            req = requestRepo.findById(event.getRequestId())
                    .orElseThrow(() -> new RuntimeException("Email request not found: " + event.getRequestId()));

            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(req.getRecipient());
            if (req.getCc() != null) helper.setCc(req.getCc());
            if (req.getBcc() != null) helper.setBcc(req.getBcc());
            helper.setSubject(req.getSubject());
            helper.setText(req.getBody(), true);

            mailSender.send(msg);
            req.setStatus(EmailRequest.Status.SENT);
            req.setSentAt(LocalDateTime.now());
        } catch (Exception ex) {
            if (req != null) {
                req.setStatus(EmailRequest.Status.FAILED);
                requestRepo.save(req);
            }
            LOGGER.error("Failed to send email for request ID: {}", event.getRequestId(), ex);
            return;
        }
        requestRepo.save(req);
    }
}

