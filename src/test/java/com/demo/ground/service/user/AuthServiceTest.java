package com.demo.ground.service.user;

import com.testing.ground.entity.society.Society;
import com.testing.ground.entity.user.*;
import com.testing.ground.repository.society.SocietyRepository;
import com.testing.ground.repository.user.*;
import com.testing.ground.service.user.AppUserSocietyMappingService;
import com.testing.ground.service.user.AuthService;
import com.testing.ground.util.CommonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private SocietyRepository societyRepository;
    @Mock
    private PermissionRepository permissionRepository;
    @Mock
    private UserRoleRepository userRoleRepository;
    @Mock
    private AppUserSocietyMappingService mappingService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_invalidUsername_throwsException() {
        Exception ex = assertThrows(RuntimeException.class, () ->
                authService.registerUser(1L, "", "Password1"));
        assertTrue(ex.getMessage().contains("Username cannot be null or empty"));
    }

    @Test
    void registerUser_invalidPassword_throwsException() {
        Exception ex = assertThrows(RuntimeException.class, () ->
                authService.registerUser(1L, "test@example.com", ""));
        assertTrue(ex.getMessage().contains("Password cannot be null or empty"));
    }

    @Test
    void registerUser_success() {
        Long societyId = 1L;
        String username = "test@example.com";
        String password = "Password1";

        Society society = new Society();
        society.setId(societyId); // Fix: set the ID
        Permission permission = new Permission();
        permission.setId(10L);
        permission.setSocietyId(societyId);
        UserRole userRole = new UserRole();

        try (MockedStatic<CommonUtil> commonUtilMock = mockStatic(CommonUtil.class)) {
            commonUtilMock.when(() -> CommonUtil.isValidEmail(username)).thenReturn(true);
            commonUtilMock.when(() -> CommonUtil.isValidPhoneNumber(username)).thenReturn(true);

            when(societyRepository.findById(societyId)).thenReturn(Optional.of(society));
            when(appUserRepository.existsBySocietyIdAndUsername(societyId, username)).thenReturn(false);
            when(passwordEncoder.encode(password)).thenReturn("hashed");
            when(permissionRepository.findBySocietyIdAndName(eq(societyId), anyString())).thenReturn(Optional.of(permission));
            when(userRoleRepository.findBySocietyIdAndName(eq(societyId), anyString())).thenReturn(Optional.of(userRole));
            when(mappingService.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

            AppUserSocietyMapping result = authService.registerUser(societyId, username, password);

            assertNotNull(result);
            assertEquals(society, result.getSociety());
            assertEquals(1, result.getRoles().size());
            verify(appUserRepository).save(any(AppUser.class));
        }
    }

    @Test
    void registerUser_userAlreadyExists_throwsException() {
        Long societyId = 1L;
        String username = "test@example.com";
        String password = "Password1";

        try (MockedStatic<CommonUtil> commonUtilMock = mockStatic(CommonUtil.class)) {
            commonUtilMock.when(() -> CommonUtil.isValidEmail(username)).thenReturn(true);
            commonUtilMock.when(() -> CommonUtil.isValidPhoneNumber(username)).thenReturn(true);

            when(societyRepository.findById(societyId)).thenReturn(Optional.of(new Society()));
            when(appUserRepository.existsBySocietyIdAndUsername(societyId, username)).thenReturn(true);

            Exception ex = assertThrows(RuntimeException.class, () ->
                    authService.registerUser(societyId, username, password));
            assertTrue(ex.getMessage().contains("Username already taken in this society"));
        }
    }

    @Test
    void registerUser_societyNotFound_throwsException() {
        Long societyId = 1L;
        String username = "test@example.com";
        String password = "Password1";

        try (MockedStatic<CommonUtil> commonUtilMock = mockStatic(CommonUtil.class)) {
            commonUtilMock.when(() -> CommonUtil.isValidEmail(username)).thenReturn(true);
            commonUtilMock.when(() -> CommonUtil.isValidPhoneNumber(username)).thenReturn(true);

            when(societyRepository.findById(societyId)).thenReturn(Optional.empty());

            Exception ex = assertThrows(RuntimeException.class, () ->
                    authService.registerUser(societyId, username, password));
            assertTrue(ex.getMessage().contains("Society with ID 1 not found"));
        }
    }

    @Test
    void registerUser_usernameExistsInSociety_throwsException() {
        Long societyId = 1L;
        String username = "existing@example.com";
        String password = "Password1";

        try (MockedStatic<CommonUtil> commonUtilMock = mockStatic(CommonUtil.class)) {
            commonUtilMock.when(() -> CommonUtil.isValidEmail(username)).thenReturn(true);
            commonUtilMock.when(() -> CommonUtil.isValidPhoneNumber(username)).thenReturn(true);

            when(societyRepository.findById(societyId)).thenReturn(Optional.of(new Society()));
            when(appUserRepository.existsBySocietyIdAndUsername(societyId, username)).thenReturn(true);

            Exception ex = assertThrows(RuntimeException.class, () ->
                    authService.registerUser(societyId, username, password));
            assertTrue(ex.getMessage().contains("Username already taken in this society"));
        }
    }

    @Test
    void registerUser_weakPassword_throwsException() {
        Long societyId = 1L;
        String username = "test@example.com";
        String weakPassword = "password"; // No uppercase, no digit

        try (MockedStatic<CommonUtil> commonUtilMock = mockStatic(CommonUtil.class)) {
            commonUtilMock.when(() -> CommonUtil.isValidEmail(username)).thenReturn(true);
            commonUtilMock.when(() -> CommonUtil.isValidPhoneNumber(username)).thenReturn(true);

            when(societyRepository.findById(societyId)).thenReturn(Optional.of(new Society()));

            Exception ex = assertThrows(RuntimeException.class, () ->
                    authService.registerUser(societyId, username, weakPassword));
            assertTrue(ex.getMessage().contains("Password must include at least one uppercase letter, one lowercase letter, and one digit"));
        }
    }
}