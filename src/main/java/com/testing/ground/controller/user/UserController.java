package com.testing.ground.controller.user;

import com.testing.ground.dto.user.UploadSummary;
import com.testing.ground.entity.user.AppUser;
import com.testing.ground.entity.user.UserDetail;
import com.testing.ground.request.user.UserDetailRequest;
import com.testing.ground.response.user.UserDetailResponse;
import com.testing.ground.service.user.AppUserService;
import com.testing.ground.service.user.BulkUserUploadService;
import com.testing.ground.service.user.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserDetailService userDetailService;
    private final BulkUserUploadService bulkUserUploadService;
    private final AppUserService appUserService;

    @PostMapping("/bulk/upload")
    public ResponseEntity<UploadSummary> uploadUsers(@RequestParam("file") MultipartFile file,
                                              @RequestParam("societyId") Long societyId,
                                              @RequestParam("createdBy") String createdBy) {
        UploadSummary uploadSummary = null;
        try {
            uploadSummary = bulkUserUploadService.processCsvFile(file, societyId, createdBy);
            if (uploadSummary.getTotal() == 0) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(uploadSummary);
            }
            return ResponseEntity.ok(uploadSummary);
        } catch (IllegalArgumentException e) {
            assert uploadSummary != null;
            uploadSummary.setMessage("Validation error: " + e.getMessage());
            return ResponseEntity.badRequest().body(uploadSummary);
        } catch (Exception e) {
            assert uploadSummary != null;
            uploadSummary.setMessage("Upload failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(uploadSummary);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> getUserDetail(@PathVariable Long id) {
        return appUserService.getUserDetailById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDetailResponse>> getAllActive() {
        List<AppUser> users = appUserService.getAllActiveUsers();
        List<UserDetailResponse> responses = users.stream()
                .map(this::toUserDetailResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailResponse> update(@PathVariable Long id, @RequestBody UserDetailRequest userDetailRequest) {
        return ResponseEntity.ok(appUserService.updateUserDetail(id, userDetailRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /*@GetMapping("/paged")
    public ResponseEntity<Page<UserDetailResponse>> getPaginated(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userDetailService.getPaginated(search, pageable));
    }*/

    private UserDetailResponse toUserDetailResponse(AppUser appUser) {
        UserDetail userDetail = appUser.getUserDetail();
        UserDetailResponse userDetailResponse = new UserDetailResponse();
        if (appUser.getUserDetail() == null) {
            return userDetailResponse; // Return empty response if no user detail is found
        }
        BeanUtils.copyProperties(userDetail, userDetailResponse, "id", "createdDate", "createdBy", "lastUpdated", "lastUpdatedBy");
        return userDetailResponse;
    }

}

