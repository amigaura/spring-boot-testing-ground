package com.testing.ground.controller;

import com.testing.ground.entity.Item;
import com.testing.ground.model.ProcessingResult;
import com.testing.ground.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {


    @Autowired
    private ItemsService itemsService;

    @PostMapping
    public ResponseEntity<ProcessingResult> createItem(@RequestBody Item item) {
        try {
            return ResponseEntity.ok(itemsService.createOrUpdateItem(item));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/process_items")
    public ResponseEntity<List<ProcessingResult>> processItems(@RequestBody List<Item> items) throws Exception {
        try {
            return ResponseEntity.ok(itemsService.processItems(items));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        try {
            return ResponseEntity.ok(itemsService.getAllItems());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}

