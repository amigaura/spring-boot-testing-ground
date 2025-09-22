package com.testing.ground.service.user;

import com.testing.ground.entity.user.UserDetail;
import com.testing.ground.repository.user.UserDetailRepository;
import com.testing.ground.request.user.UserDetailRequest;
import com.testing.ground.response.user.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private final UserDetailRepository userDetailRepository;

    /*@Override
    public UserDetailResponse createUserDetail(UserDetailRequest userDetailRequest) {
        UserDetail userDetail = mapToEntity(userDetailRequest);
        userDetail.setCreatedDate(LocalDateTime.now());
        userDetail.setLastUpdated(LocalDateTime.now());
        userDetail.setLastUpdatedBy(userDetailRequest.getCreatedBy());
        return mapToResponse(userDetailRepository.save(userDetail));
    }*/

    @Override
    public UserDetailResponse getUserDetailById(Long id) {
        return userDetailRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("UserDetail not found with id: " + id));
    }

    @Override
    public List<UserDetailResponse> getAllUserDetails() {
        return userDetailRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailResponse updateUserDetail(Long id, UserDetailRequest userDetailRequest) {
        UserDetail existing = userDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserDetail not found"));

        // Update relevant fields
        BeanUtils.copyProperties(userDetailRequest, existing, "id", "createdDate", "createdBy");
        existing.setLastUpdated(LocalDateTime.now());
        existing.setLastUpdatedBy(userDetailRequest.getCreatedBy());
        return mapToResponse(userDetailRepository.save(existing));
    }

    @Override
    public void deleteUserDetail(Long id) {
        userDetailRepository.deleteById(id);
    }

    @Override
    public Page<UserDetailResponse> getPaginated(String search, Pageable pageable) {
        Page<UserDetail> page;

        if (search != null && !search.isBlank()) {
            page = userDetailRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            page = userDetailRepository.findAll(pageable);
        }

        return page.map(this::mapToResponse);
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

