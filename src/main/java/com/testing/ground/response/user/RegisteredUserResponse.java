package com.testing.ground.response.user;

import com.testing.ground.entity.user.AppUser;
import lombok.Data;

@Data
public class RegisteredUserResponse {
    private String username;
    private Long societyId;
    private Long userId;
    private String accessToken;
    private String refreshToken;

    public RegisteredUserResponse(String username, Long societyId, Long userId, String accessToken, String refreshToken) {
        this.username = username;
        this.societyId = societyId;
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
