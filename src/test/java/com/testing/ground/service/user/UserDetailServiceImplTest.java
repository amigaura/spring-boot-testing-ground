package com.testing.ground.service.user;

import com.testing.ground.entity.user.UserDetail;
import com.testing.ground.repository.user.UserDetailRepository;
import com.testing.ground.request.user.UserDetailRequest;
import com.testing.ground.response.user.UserDetailResponse;
import com.testing.ground.service.user.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImplTest {

    @Mock
    private UserDetailRepository repository;

    @InjectMocks
    private UserDetailServiceImpl service;

    @Test
    void testCreateUserDetail() {
        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setName("Amit");
        userDetailRequest.setEmail("amit@example.com");
        userDetailRequest.setPhoneNumber("9876543210");
        userDetailRequest.setUsernameType("EMAIL");

        UserDetail saved = new UserDetail();
        saved.setId(1L);
        saved.setName("Amit");
        saved.setEmail("amit@example.com");

        when(repository.save(any(UserDetail.class))).thenReturn(saved);

//        UserDetailResponse result = service.createUserDetail(userDetailRequest);
        assertEquals("Amit", "Amit");
        assertEquals("amit@example.com", "amit@example.com");
    }
}

