package com.testing.ground.service.user;

import com.testing.ground.entity.user.AppUser;
import com.testing.ground.entity.user.UserDetail;
import com.testing.ground.request.user.UserDetailRequest;
import com.testing.ground.response.user.UserDetailResponse;

import java.util.List;
import java.util.Optional;

public interface AppUserService {


    Optional<UserDetail> getUserDetailById(Long id);

    Optional<AppUser> getUserByUsername(String username);

    List<AppUser> getAllUsers();

    List<AppUser> getAllActiveUsers();

//    AppUser updateUser(Long id, AppUser updatedUser);

    void deleteUser(Long id);

    UserDetailResponse updateUserDetail(Long id, UserDetailRequest userDetailRequest);
}
