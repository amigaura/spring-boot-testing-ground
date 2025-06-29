package com.testing.ground.service.user;

import com.testing.ground.constant.user.RegistrationDefaults;
import com.testing.ground.entity.society.Society;
import com.testing.ground.entity.user.*;
import com.testing.ground.repository.society.SocietyRepository;
import com.testing.ground.repository.user.PermissionRepository;
import com.testing.ground.repository.user.RefreshTokenRepository;
import com.testing.ground.repository.user.AppUserRepository;
import com.testing.ground.repository.user.UserRoleRepository;
import com.testing.ground.response.user.AuthResponse;
import com.testing.ground.response.user.MultipleSocietiesResponse;
import com.testing.ground.util.CommonUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthService {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private JwtService jwtUtil;

    @Autowired
    private AppUserSocietyMappingService mappingService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SocietyRepository societyRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

//    public Map<String, String> login(String username, String password) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            String accessToken = jwtUtil.generateToken(username);
//
//            AppUser user = appUserRepository.findByUsername(username)
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            String refreshToken = generateRefreshToken(user);
//
//            return Map.of(
//                    "accessToken", accessToken,
//                    "refreshToken", refreshToken
//            );
//
//        } catch (BadCredentialsException ex) {
//            throw new RuntimeException("Invalid username or password", ex);
//        }
//    }

    public ResponseEntity<?> authenticateUser(String username, String password) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
        if (!passwordEncoder.matches(password, user.getCredential().getPasswordHash())) {
            throw new BadCredentialsException("Invalid password");
        }

        List<AppUserSocietyMapping> mappings = mappingService.getMappingsForUser(user);
        if (mappings.size() > 1) {
            return ResponseEntity.ok(new MultipleSocietiesResponse(mappings));
        } else {
            AppUserSocietyMapping mapping = mappings.get(0);

            String accessToken = jwtUtil.generateToken(user, mapping);
            String refreshToken = generateRefreshToken(user);

            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
        }
    }

    public String generateRefreshToken(AppUser user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        String refreshTokenStr = UUID.randomUUID().toString();
        refreshToken.setToken(refreshTokenStr);
        refreshToken.setExpiryDate(Instant.now().plus(Duration.ofDays(7)));
        refreshTokenRepository.save(refreshToken);
        return refreshTokenStr;
    }

    @Transactional
    public AppUserSocietyMapping registerUser(Long societyId, String username, String password) {
        validateUserProvidedParams(societyId, username, password);

        // Load society upfront (single DB call)
        Society society = societyRepository.findById(societyId)
                .orElseThrow(() -> new IllegalArgumentException("Society with ID " + societyId + " not found"));

        // Try to fetch existing AppUser by username
        Optional<AppUser> optionalUser = appUserRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            AppUser existingUser = optionalUser.get();

            // Check if mapping already exists
            if (mappingService.existsMappingForUserAndSociety(existingUser, societyId)) {
                LOGGER.debug("User {} already mapped to society {}", username, societyId);
                throw new IllegalStateException("User already registered in this society");
            }

            // Create a new mapping for existing user in a new society
            LOGGER.debug("Creating new society mapping for existing user {}", username);
            return createMapping(existingUser, society);
        }

        // Ensure username is not used globally for another user
        if (appUserRepository.existsBySocietyIdAndUsername(societyId, username)) {
            throw new IllegalStateException("Username already taken in this society");
        }

        // Create a new user and persist credentials
        AppUser newUser = new AppUser();
        newUser.setUsername(username);

        UserCredential credential = new UserCredential();
        credential.setPasswordHash(passwordEncoder.encode(password));
        credential.setAppUser(newUser);
        newUser.setCredential(credential);

        UserDetail detail = new UserDetail();
        newUser.setUserDetail(detail);
        newUser.setSocietyId(societyId);

        appUserRepository.save(newUser);

        // Create mapping to society
        AppUserSocietyMapping mapping = createMapping(newUser, society);

        LOGGER.info("New user '{}' registered and mapped to society {}", username, societyId);
        return mapping;
    }

    private AppUserSocietyMapping createMapping(AppUser user, Society society) {
        Long societyId = society.getId();

        Permission permission = permissionRepository.findBySocietyIdAndName(societyId, RegistrationDefaults.DEFAULT_PERMISSION.getValue())
                .orElseThrow(() -> new RuntimeException("Default permission not found for society " + societyId));

        UserRole role = userRoleRepository.findBySocietyIdAndName(societyId, RegistrationDefaults.DEFAULT_ROLE.getValue())
                .orElseThrow(() -> new RuntimeException("Default role not found for society " + societyId));

        role.setPermissionIds(Set.of(permission.getId()));

        AppUserSocietyMapping mapping = new AppUserSocietyMapping();
        mapping.setSociety(society);
        mapping.setAppUser(user);
        mapping.setRoles(Set.of(role));
        mapping.setJoinedAt(LocalDateTime.now());
        mapping.setLocalUserId(UUID.randomUUID().toString());
        mapping.setActive(true);

        return mappingService.save(mapping);
    }

    private void validateUserProvidedParams(Long societyId, String username, String password) {
        // Validate society ID
        if (societyId == null || societyId <= 0) {
            throw new IllegalArgumentException("Society ID must be a positive number");
        }

        // Validate username
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        String trimmedUsername = username.trim();
        if (trimmedUsername.length() < 3 || trimmedUsername.length() > 20) {
            throw new IllegalArgumentException("Username must be between 3 and 20 characters");
        }

        boolean isEmail = CommonUtil.isValidEmail(trimmedUsername);
        boolean isPhone = CommonUtil.isValidPhoneNumber(trimmedUsername);

        if (!isEmail && !isPhone) {
            LOGGER.warn("Invalid username format: {}", trimmedUsername);
            throw new IllegalArgumentException("Username must be a valid email or phone number");
        }

        LOGGER.debug("Username validated as {}: {}", isEmail ? "email" : "phone", trimmedUsername);

        // Validate password
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        String complexityRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
        if (!password.matches(complexityRegex)) {
            throw new IllegalArgumentException("Password must include at least one uppercase letter, one lowercase letter, and one digit");
        }

        LOGGER.debug("Password validation passed for user: {}", trimmedUsername);
    }

    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }

}
