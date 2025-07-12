package com.testing.ground.service.misc;

import com.testing.ground.dto.misc.EmailSendEvent;
import com.testing.ground.entity.misc.EmailRequest;
import com.testing.ground.entity.misc.EmailTemplate;
import com.testing.ground.producer.EmailProducer;
import com.testing.ground.repository.misc.EmailRequestRepository;
import com.testing.ground.repository.misc.EmailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${kafka.email-send-topic.topic-name}")
    private String emailSendTopicName;

    private final EmailTemplateRepository emailTemplateRepository;
    private final EmailRequestRepository emailRequestRepository;
    private final TemplateRenderService templateRenderService;
    private final JavaMailSender mailSender;
//    private final ApplicationEventPublisher events;
    private final EmailProducer emailProducer;

    @Override
    public void send(String to, String templateCode, Map<String, Object> vars) {
        EmailTemplate tmpl = emailTemplateRepository.findByCode(templateCode)
                .orElseThrow(() -> new RuntimeException("Template not found: " + templateCode));

        String subject = templateRenderService.render(tmpl.getSubjectTemplate(), vars);
        String body    = templateRenderService.render(tmpl.getBodyTemplate(), vars);
        LOGGER.debug("Sending email to: {}, Subject: {}", to, subject);
        LOGGER.debug("Email body: {}", body);

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setRecipient(to);
        emailRequest.setSubject(subject);
        emailRequest.setBody(body);
        emailRequestRepository.save(emailRequest);

//        events.publishEvent(new EmailSendEvent(this, emailRequest.getId()));
        emailProducer.publishEmailSendEvent(emailSendTopicName, new EmailSendEvent(to, subject, body, this, emailRequest.getId()));
    }

//    @Override
//    public void send(EmailRequest request) {
//        emailRequestRepository.save(request);
//        events.publishEvent(new EmailSendEvent(this, request.getId()));
//    }
}

