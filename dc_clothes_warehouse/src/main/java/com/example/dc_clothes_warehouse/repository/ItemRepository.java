package com.example.dc_clothes_warehouse.repository;

import com.example.dc_clothes_warehouse.model.ItemModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemModel, Integer>{
    
}
