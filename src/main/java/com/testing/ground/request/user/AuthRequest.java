package com.testing.ground.request.user;

import lombok.Data;

@Data
public class AuthRequest {
    private Long societyId;
    private String username;
    private String password;
}


