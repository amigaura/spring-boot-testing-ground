package com.testing.ground.service.user;

import com.testing.ground.constant.user.RegistrationDefaults;
import com.testing.ground.constant.user.UserStatus;
import com.testing.ground.dto.user.UploadSummary;
import com.testing.ground.entity.society.Society;
import com.testing.ground.entity.user.*;
import com.testing.ground.repository.society.SocietyRepository;
import com.testing.ground.repository.user.AppUserRepository;
import com.testing.ground.repository.user.PermissionRepository;
import com.testing.ground.repository.user.UserRoleRepository;
import com.testing.ground.util.UsernameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Service
public class BulkUserUploadService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    SocietyRepository societyRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    AuthService authService;

    @Autowired
    AuditLogger auditLogger;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UploadSummary processCsvFile(MultipartFile file, Long societyId, String createdBy) throws Exception {

        if (file.isEmpty() || file.getSize() == 0) {
            throw new IllegalArgumentException("File is empty. Please upload a valid CSV file.");
        }

        if (file.getSize() > 2 * 1024 * 1024) { // 2 MB size limit
            throw new IllegalArgumentException("File size exceeds the 2MB limit.");
        }

        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".csv")) {
            throw new IllegalArgumentException("Invalid file type. Please upload a CSV file.");
        }

        if (societyId == null || societyId <= 0) {
            throw new IllegalArgumentException("Invalid society ID provided.");
        }

        UploadSummary summary = new UploadSummary();
        summary.setSocietyId(societyId);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            boolean isHeader = true;

            Permission permission = permissionRepository.findBySocietyIdAndName(societyId, RegistrationDefaults.DEFAULT_PERMISSION.getValue())
                    .orElseThrow(() -> new RuntimeException("Default permission not found for society " + societyId));

            UserRole role = userRoleRepository.findBySocietyIdAndName(societyId, RegistrationDefaults.DEFAULT_ROLE.getValue())
                    .orElseThrow(() -> new RuntimeException("Default role not found for society " + societyId));

            role.setPermissionIds(Set.of(permission.getId()));

            String line;
            int lineNum = 1;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                summary.setTotal(summary.getTotal() + 1);

                try {
                    String[] tokens = line.split(",");
                    if (tokens.length < 3) {
                        throw new IllegalArgumentException("Missing required fields: name, email, phone");
                    }

                    // Basic CSV mapping: name, email, phone, societyId, createdBy
                    String name = tokens[0].trim();
                    String email = tokens[1].trim();
                    String phone = tokens[2].trim();

                    if (!UsernameValidator.isEmail(email) && !UsernameValidator.isPhone(phone)) {
                        throw new IllegalArgumentException("Missing valid email or phone in line: " + line);
                    }

                    String username = UsernameValidator.isEmail(email) ? email : phone;

                    AppUser user = new AppUser();
                    user.setSocietyId(societyId);
                    user.setUsername(username);
                    user.setStatus(UserStatus.PENDING_VERIFICATION);

                    UserDetail detail = new UserDetail();
                    detail.setName(name);
                    detail.setEmail(email);
                    detail.setPhoneNumber(phone);
                    String usernameType = UsernameValidator.isEmail(email) ? "EMAIL" : "PHONE";
                    detail.setUsernameType(usernameType);
                    detail.setCreatedBy(createdBy);
                    detail.setCreatedDate(LocalDateTime.now());
                    user.setUserDetail(detail);

                    UserCredential credential = new UserCredential();
                    credential.setPasswordHash(passwordEncoder.encode(RegistrationDefaults.DEFAULT_PASSWORD.getValue()));
                    credential.setAppUser(user);
                    user.setCredential(credential);

                    appUserRepository.save(user);
                    // Link to society
                    Society society = societyRepository.findById(societyId)
                            .orElseThrow(() -> new IllegalArgumentException("Society with ID " + societyId + " not found"));
                    // Create mapping and save user
                    authService.createMapping(user, society);

                    // Audit log for success
                    auditLogger.log("USER_UPLOAD", createdBy, user.getUsername(), societyId);

                    summary.setSuccess(summary.getSuccess() + 1);

                } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                    summary.setFailed(summary.getFailed() + 1);
                    summary.getErrorLines().add("Line " + lineNum + ": Duplicate entry for user - " + line);
                } catch (Exception e) {
                    summary.setFailed(summary.getFailed() + 1);
                    summary.getErrorLines().add("Line " + lineNum + ": " + e.getMessage());
                }
            }
            if (summary.getTotal() == 0) {
                summary.setMessage("No valid user data found in the file.");
            } else if (summary.getSuccess() == 0) {
                summary.setMessage("All entries failed to upload. Check error lines for details.");
            } else if (summary.getFailed() > 0) {
                summary.setMessage("Upload completed with " + summary.getSuccess() + " users created and " + summary.getFailed() + " errors. Check error lines for details.");
            } else {
                summary.setMessage("Upload completed successfully with " + summary.getSuccess() + " users created.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error processing file: " + e.getMessage(), e);
        } finally {
            auditLogger.log("BULK_USER_UPLOAD_SUMMARY", createdBy, "Total: " + summary.getTotal() +
                    ", Success: " + summary.getSuccess() +
                    ", Failed: " + summary.getFailed() +
                    ", Errors: " + summary.getErrorLines().size(), societyId);
        }
        return summary;
    }
}
