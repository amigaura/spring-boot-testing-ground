package com.testing.ground.controller;

import com.testing.ground.entity.Item;
import com.testing.ground.service.ItemsServiceAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items/async")
public class ItemControllerAsync {

    @Autowired
    private ItemsServiceAsync itemsServiceAsync;

    @PostMapping("/process-items")
    public ResponseEntity<Map<String, String>> processItems(@RequestBody List<Item> items) {
        String requestId = itemsServiceAsync.processItemsAsync(items);
        return ResponseEntity.ok(Map.of("request_id", requestId));
    }
}

