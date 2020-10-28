package com.bawa.projectmanaement.service;

import com.bawa.projectmanaement.domain.Item;
import com.bawa.projectmanaement.exception.MaintananceExeption;
import com.bawa.projectmanaement.exception.OrderNotValidExeption;
import com.bawa.projectmanaement.repository.ItemRepository;
import com.bawa.projectmanaement.types.ItemStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


import dto.ItemDTO;
import dto.OrderDTO;

@Service
public class OrderService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ItemService itemService;

    
    private static Logger log = LoggerFactory.getLogger(OrderService.class);

    public ItemStatus proceedOrder(OrderDTO itemRequest) {
        ItemStatus status = itemService.tryToUpdateQuantity(itemRequest);
        if (status==ItemStatus.ITEM_UPDATED) {
            makeOrder(itemRequest);
        }
        return status;
    }

    private ItemDTO makeOrder(OrderDTO itemRequest) {
        try {
            HttpEntity<OrderDTO> newRequest = new HttpEntity<>(itemRequest);
            log.info("items updated...");
            return restTemplate.postForObject("http://db-service/order/new", newRequest, ItemDTO.class);

        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode()==HttpStatus.BAD_REQUEST) {
                throw new OrderNotValidExeption(ex.getResponseBodyAsString());
            }else{
                throw new MaintananceExeption("Request could not be completed. Please Contact..");
            }
        }
    }

}
