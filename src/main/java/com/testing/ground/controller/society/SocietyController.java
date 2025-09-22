package com.testing.ground.controller.society;

import com.testing.ground.entity.society.Society;
import com.testing.ground.service.society.SocietyService;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/societies")
public class SocietyController {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SocietyController.class);

    @Autowired
    private SocietyService societyService;

    @PostMapping
    public ResponseEntity<Society> addSociety(@RequestBody Society society) {
        return ResponseEntity.ok(societyService.addSociety(society));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Society> getSocietyById(@PathVariable Long id) {
        return societyService.getSocietyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Society>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(societyService.searchSocietiesByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Society> updateSociety(@PathVariable Long id, @RequestBody Society society) {
        return ResponseEntity.ok(societyService.updateSociety(id, society));
    }

    @PutMapping("/{id}/suspend")
    public ResponseEntity<Society> suspendSociety(@PathVariable Long id) {
        return ResponseEntity.ok(societyService.suspendSociety(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSociety(@PathVariable Long id) {
        societyService.deleteSociety(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleJwtSignatureException(SignatureException ex) {
        LOGGER.error("Invalid JWT signature: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid or tampered JWT token. Please login again.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        LOGGER.error("Access denied: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("You do not have permission to access this resource.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        LOGGER.error("Invalid argument: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid input provided. Please check your request.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        LOGGER.error("An unexpected error occurred: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred. Please try again later.");
    }
}

