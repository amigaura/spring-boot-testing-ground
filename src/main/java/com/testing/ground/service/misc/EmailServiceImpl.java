package com.testing.ground.service.misc;

import com.testing.ground.dto.misc.EmailSendEvent;
import com.testing.ground.dto.user.PasswordResetEmailDTO;
import com.testing.ground.entity.misc.EmailRequest;
import com.testing.ground.entity.misc.EmailTemplate;
import com.testing.ground.repository.misc.EmailRequestRepository;
import com.testing.ground.repository.misc.EmailTemplateRepository;
import com.testing.ground.service.user.AuditLogger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailTemplateRepository templateRepo;
    private final EmailRequestRepository requestRepo;
    private final TemplateRenderService renderService;
    private final JavaMailSender mailSender;
    private final ApplicationEventPublisher events;

    @Override
    public void send(String to, String templateCode, Map<String, Object> vars) {
        EmailTemplate tmpl = templateRepo.findByCode(templateCode)
                .orElseThrow(() -> new RuntimeException("Template not found: " + templateCode));

        String subject = renderService.render(tmpl.getSubjectTemplate(), vars);
        String body    = renderService.render(tmpl.getBodyTemplate(), vars);

        EmailRequest req = new EmailRequest();
        req.setRecipient(to);
        req.setSubject(subject);
        req.setBody(body);
        requestRepo.save(req);

        events.publishEvent(new EmailSendEvent(this, req.getId()));
    }

    @Override
    public void send(EmailRequest request) {
        requestRepo.save(request);
        events.publishEvent(new EmailSendEvent(this, request.getId()));
    }
}

