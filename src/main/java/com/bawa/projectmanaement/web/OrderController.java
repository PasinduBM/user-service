package com.bawa.projectmanaement.web;

import com.bawa.projectmanaement.service.MapValidationErrorService;
import com.bawa.projectmanaement.service.OrderService;
import com.bawa.projectmanaement.types.ItemStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import dto.OrderDTO;



@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    
    @Autowired
    private MapValidationErrorService error;

    @Autowired
    private OrderService service;

    @PostMapping("/new")
    public ResponseEntity<?> ceateNewProject(@Validated @RequestBody OrderDTO itemRequest, BindingResult result){
        ResponseEntity<?> erroList = error.errorList(result);
        if(erroList!=null){
            return erroList;
        }
        ItemStatus status = service.proceedOrder(itemRequest);
        if (status==ItemStatus.ITEM_UPDATED) {
            return new ResponseEntity<String>("Order Created..", HttpStatus.CREATED);
        }else if(status==ItemStatus.ITEM_DOES_NOT_EXSIT){
            return new ResponseEntity<String>("item dosent exist", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("item currntly not available", HttpStatus.CREATED);
        }
        
    }

}

