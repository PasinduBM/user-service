package com.bawa.projectmanaement.repository;

import com.bawa.projectmanaement.domain.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long>{

    public Item findByItemName(String itemName);
    
}