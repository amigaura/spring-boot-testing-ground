package com.testing.ground.sync.controller;

import com.testing.ground.sync.model.ProcessingResult;
import com.testing.ground.sync.service.ItemProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BatchProcessingController {

    @Autowired
    private ItemProcessorService processorService;

    @PostMapping("/process-items")
    public ResponseEntity<List<ProcessingResult>> processItems(@RequestBody List<String> items) throws Exception {
        return ResponseEntity.ok(processorService.processItems(items));
    }
}

