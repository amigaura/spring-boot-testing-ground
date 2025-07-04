package com.testing.ground.controller.user;

import com.testing.ground.service.user.BulkUserUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users/bulk")
@RequiredArgsConstructor
public class BulkUserUploadController {

    private final BulkUserUploadService bulkUserUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadUsers(@RequestParam("file") MultipartFile file,
                                              @RequestParam("societyId") Long societyId,
                                              @RequestParam("createdBy") String createdBy) {
        try {
            bulkUserUploadService.processCsvFile(file, societyId, createdBy);
            return ResponseEntity.ok("Bulk upload successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
        }
    }

}

