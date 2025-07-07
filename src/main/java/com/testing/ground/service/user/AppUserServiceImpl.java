package com.testing.ground.service.user;

import com.testing.ground.constant.user.UserStatus;
import com.testing.ground.entity.user.AppUser;
import com.testing.ground.entity.user.UserDetail;
import com.testing.ground.repository.user.AppUserRepository;
import com.testing.ground.repository.user.UserDetailRepository;
import com.testing.ground.request.user.UserDetailRequest;
import com.testing.ground.response.user.UserDetailResponse;
import com.testing.ground.util.FieldUpdaterUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    Logger LOGGER = LoggerFactory.getLogger(AppUserServiceImpl.class);

    private final AppUserRepository appUserRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public Optional<UserDetail> getUserDetailById(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        // Check if user is active
        if (!UserStatus.ACTIVE.name().equalsIgnoreCase(appUser.getStatus().name())) {
            throw new IllegalStateException("User is not active (id: " + id + ")");
        }
        return Optional.ofNullable(appUser.getUserDetail());
    }

    @Override
    public Optional<AppUser> getUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppUser> getAllActiveUsers() {
        return appUserRepository.findAllByStatus("ACTIVE");
    }

    @Override
    public void deleteUser(Long id) {
        // Check if user exists
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Mark user status as DELETED
        appUser.setStatus(UserStatus.DELETED);
        appUserRepository.save(appUser);

        // Optionally, mark user detail as deleted or handle as needed
        UserDetail userDetail = appUser.getUserDetail();
        LOGGER.debug("Deleting user detail for user with app user id: {} and uder detail id: {}", id, userDetail != null ? userDetail.getId() : "N/A");
        if (userDetail != null) {
            userDetailRepository.delete(userDetail);
        }
    }

    @Override
    public UserDetailResponse updateUserDetail(Long id, UserDetailRequest userDetailRequest) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // check user's status
        if (!UserStatus.ACTIVE.name().equalsIgnoreCase(appUser.getStatus().name())) {
            throw new IllegalStateException("Cannot update details for user with non-active status (id: " + id + ")");
        }

        UserDetail existingUserDetail = appUser.getUserDetail();
        if (existingUserDetail == null) {
            throw new EntityNotFoundException("UserDetail not found for user with id: " + id);
        }

        // Update relevant fields
        FieldUpdaterUtil.updateNonNullFields(userDetailRequest, existingUserDetail);
        existingUserDetail.setLastUpdated(LocalDateTime.now());
        existingUserDetail.setLastUpdatedBy(userDetailRequest.getCreatedBy());
        return mapToResponse(userDetailRepository.save(existingUserDetail));
    }

    private UserDetail mapToEntity(UserDetailRequest userDetailRequest) {
        UserDetail entity = new UserDetail();
        BeanUtils.copyProperties(userDetailRequest, entity);
        return entity;
    }

    private UserDetailResponse mapToResponse(UserDetail entity) {
        UserDetailResponse userDetailResponse = new UserDetailResponse();
        BeanUtils.copyProperties(entity, userDetailResponse);
        return userDetailResponse;
    }


}
