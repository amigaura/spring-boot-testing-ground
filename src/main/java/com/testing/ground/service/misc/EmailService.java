package com.testing.ground.service.misc;

import com.testing.ground.dto.user.PasswordResetEmailDTO;
import com.testing.ground.entity.misc.EmailRequest;

import java.util.Map;

public interface EmailService {
    void send(String to, String templateCode, Map<String, Object> variables);
//    void send(EmailRequest request);
}
