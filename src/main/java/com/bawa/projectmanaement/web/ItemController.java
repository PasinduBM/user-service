package com.bawa.projectmanaement.web;

import com.bawa.projectmanaement.domain.Item;
import com.bawa.projectmanaement.repository.ItemRepository;
import com.bawa.projectmanaement.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.ItemDTO;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping("/new")
    public ResponseEntity<Item> addItem(@RequestBody ItemDTO item) {
        Item savedItem = service.saveItem(item);
        return new ResponseEntity(savedItem,HttpStatus.CREATED);
    }
    
}