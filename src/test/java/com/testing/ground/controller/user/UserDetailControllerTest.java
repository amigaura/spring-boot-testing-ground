package com.testing.ground.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.ground.config.SecurityTestConfig;
import com.testing.ground.repository.society.SocietyRepository;
import com.testing.ground.repository.user.*;
import com.testing.ground.request.user.UserDetailRequest;
import com.testing.ground.response.user.UserDetailResponse;
import com.testing.ground.service.user.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(UserController.class)
@ComponentScan(basePackages = "com.testing.ground.controller.user")
@Import(SecurityTestConfig.class)
class UserDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDetailService userDetailService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UserDetailService userDetailService() {
            return Mockito.mock(UserDetailService.class);
        }

        @Bean
        public AuthService authService() {
            return Mockito.mock(AuthService.class);
        }

        @Bean
        public AuthenticationManager authenticationManager() {
            return Mockito.mock(AuthenticationManager.class);
        }

        @Bean
        public RefreshTokenRepository refreshTokenRepository() {
            return Mockito.mock(RefreshTokenRepository.class);
        }

        @Bean
        public AppUserRepository appUserRepository() {
            return Mockito.mock(AppUserRepository.class);
        }

        @Bean
        public JwtService jwtService() {
            return Mockito.mock(JwtService.class);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return Mockito.mock(PasswordEncoder.class);
        }

        @Bean
        public AppUserSocietyMappingService appUserSocietyMappingService() {
            return Mockito.mock(AppUserSocietyMappingService.class);
        }

        @Bean
        public SocietyRepository societyRepository() {
            return Mockito.mock(SocietyRepository.class);
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }

        @Bean
        public PermissionRepository permissionRepository() {
            return Mockito.mock(PermissionRepository.class);
        }

        @Bean
        public UserRoleRepository userRoleRepository() {
            return Mockito.mock(UserRoleRepository.class);
        }

        @Bean
        public BulkUserUploadService bulkUserUploadService() {
            return Mockito.mock(BulkUserUploadService.class);
        }

        @Bean
        public PasswordResetService passwordResetService() {
            return Mockito.mock(PasswordResetService.class);
        }

        @Bean
        public PasswordChangeService passwordChangeService() {
            return Mockito.mock(PasswordChangeService.class);
        }

        @Bean
        public PasswordHistoryRepository passwordHistoryRepository() {
            return Mockito.mock(PasswordHistoryRepository.class);
        }

        @Bean
        public AuditLogger auditLogger() {
            return Mockito.mock(AuditLogger.class);
        }

        @Bean
        public PasswordPolicyRepository passwordPolicyRepository() {
            return Mockito.mock(PasswordPolicyRepository.class);
        }

        @Bean
        public UserCredentialService userCredentialService() {
            return Mockito.mock(UserCredentialService.class);
        }

        @Bean
        public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
            return Mockito.mock(org.springframework.security.core.userdetails.UserDetailsService.class);
        }

    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
//    @WithMockUser(roles = "ADMIN")
    void testCreateUser() throws Exception {
        UserDetailRequest request = new UserDetailRequest();
        request.setName("John");
        request.setEmail("john@example.com");
        request.setPhoneNumber("9876543210");
        request.setUsernameType("EMAIL");

//        UserDetailResponse response = new UserDetailResponse();
//        response.setId(1L);
//        response.setName("John");
//
//        Mockito.when(userDetailService.createUserDetail(any())).thenReturn(response);

        String result = mockMvc.perform(post("/api/user-details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response: " + result);
    }
}


