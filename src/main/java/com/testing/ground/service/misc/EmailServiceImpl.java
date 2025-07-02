package com.testing.ground.service.misc;

import com.testing.ground.dto.user.PasswordResetEmailDTO;
import com.testing.ground.service.user.AuditLogger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AuditLogger auditLogger;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${app.reset-password.base-url}")
    private String resetBaseUrl;

    @Retryable(
            value = MailSendException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    @Override
    public void sendPasswordResetEmail(PasswordResetEmailDTO dto) {
       /* SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getRecipientEmail());
        message.setSubject(dto.getSubject());
        message.setText("Click the link to reset your password:\n" +
                dto.getResetLink() + "?token=" + dto.getToken() + "&societyId=" + dto.getSocietyId());

        mailSender.send(message);*/

        Context context = new Context();
        context.setVariable("resetLink", dto.getResetLink());
        context.setVariable("username", dto.getRecipientEmail());

        String htmlContent = templateEngine.process("reset-password-template", context);

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(dto.getRecipientEmail());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSendException("Failed to send email", e);
        }
    }

    @Recover
    public void recover(MailSendException ex, PasswordResetEmailDTO dto) {
        LOGGER.error("Failed to send password reset email to {}", dto.getRecipientEmail(), ex);
        auditLogger.log(
                "EMAIL_SEND_FAILURE",
                "Failed to send reset email to " + dto.getRecipientEmail(),
                "system",
                dto.getSocietyId()
        );
    }

}

