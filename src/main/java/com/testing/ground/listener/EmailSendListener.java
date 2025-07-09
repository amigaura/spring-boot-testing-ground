package com.testing.ground.listener;

import com.testing.ground.dto.misc.EmailSendEvent;
import com.testing.ground.entity.misc.EmailRequest;
import com.testing.ground.repository.misc.EmailRequestRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EmailSendListener {

    Logger LOGGER = LoggerFactory.getLogger(EmailSendListener.class);
    private final EmailRequestRepository requestRepo;
    private final JavaMailSender mailSender;

    @Async
    @EventListener
    public void onEmailSend(EmailSendEvent event) {
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
