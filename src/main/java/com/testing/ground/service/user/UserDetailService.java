package com.testing.ground.service.user;

import com.testing.ground.request.user.UserDetailRequest;
import com.testing.ground.response.user.UserDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDetailService {
//    UserDetailResponse createUserDetail(UserDetailRequest userDetailRequest);
    UserDetailResponse getUserDetailById(Long id);
    List<UserDetailResponse> getAllUserDetails();
    UserDetailResponse updateUserDetail(Long id, UserDetailRequest userDetailRequest);
    void deleteUserDetail(Long id);
    Page<UserDetailResponse> getPaginated(String search, Pageable pageable);

}

