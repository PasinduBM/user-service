package com.bawa.projectmanaement.service;

import com.bawa.projectmanaement.domain.Item;
import com.bawa.projectmanaement.repository.ItemRepository;
import com.bawa.projectmanaement.types.ItemStatus;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.ItemDTO;
import dto.OrderDTO;

@Service
public class ItemService {

    @Autowired
    ItemRepository repo;

    @Autowired
    ModelMapper map;

    public Item saveItem(ItemDTO item){
        Item newItem = map.map(item, Item.class);
        return repo.save(newItem);
    }

    @Transactional
    public ItemStatus tryToUpdateQuantity(OrderDTO itemRequest){
        Item item = repo.findByItemName(itemRequest.getItemName());
        if (item==null) {
            return ItemStatus.ITEM_DOES_NOT_EXSIT;
        }else{
            int required = Integer.parseInt(itemRequest.getQuantity());
            if (item.getQuantity()>=required) {
                item.setQuantity(item.getQuantity()-required);
                repo.save(item);
                return ItemStatus.ITEM_UPDATED;
            }else{
                return ItemStatus.ITEM_OUT_OF_STOCK;
            }
        }
    }
}