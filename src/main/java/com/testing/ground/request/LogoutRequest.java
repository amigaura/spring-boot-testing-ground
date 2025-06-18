package com.testing.ground.request;

import lombok.Data;

@Data
public class LogoutRequest {
    private String refreshToken;
}
