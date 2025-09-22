package com.testing.ground.request.user;

import lombok.Data;

@Data
public class LogoutRequest {
    private String refreshToken;
}
