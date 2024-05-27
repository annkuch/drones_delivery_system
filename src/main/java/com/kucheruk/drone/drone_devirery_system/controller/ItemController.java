package com.kucheruk.drone.drone_devirery_system.controller;

import com.kucheruk.drone.drone_devirery_system.data.response.ApiResponse;
import com.kucheruk.drone.drone_devirery_system.model.Item;
import com.kucheruk.drone.drone_devirery_system.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/item/")
public class ItemController {
    @Autowired
        private ItemService itemService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody Item param)
    {
        Item item = itemService.register(param);
        ApiResponse response = new ApiResponse("success", "Item created successfully", item);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<ApiResponse> list()
    {
        List<Item> items =  itemService.findAll();
        ApiResponse response = new ApiResponse("success", "Items loaded successfully", items);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
