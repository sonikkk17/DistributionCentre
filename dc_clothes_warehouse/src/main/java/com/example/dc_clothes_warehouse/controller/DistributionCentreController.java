package com.example.dc_clothes_warehouse.controller;


import com.example.dc_clothes_warehouse.model.DistributionCentre;
import com.example.dc_clothes_warehouse.model.ItemModel;
import com.example.dc_clothes_warehouse.repository.DistributionCentreRepository;
import com.example.dc_clothes_warehouse.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/dc", produces = "application/json")
public class DistributionCentreController {
    
    private final DistributionCentreRepository distributionCentreRepository;
    private final ItemRepository itemRepository;

    public DistributionCentreController(DistributionCentreRepository distributionCentreRepository, ItemRepository itemRepository){
        this.distributionCentreRepository = distributionCentreRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<DistributionCentre> allCentres(){
        
        return (List<DistributionCentre>) distributionCentreRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<ItemModel> getItemsForCentre(@PathVariable int id) {
        var currentDistributionCentre = distributionCentreRepository.findById(id);
        var items = currentDistributionCentre.get().getItem();
        return items;
    }

    @PostMapping("/{id}")
    public ItemModel addItemToCentre(@PathVariable int id, @RequestBody ItemModel item) {
        var currentDistributionCentre = distributionCentreRepository.findById(id);
        item.setDistributionCentre(currentDistributionCentre.get());
        var savedItem = itemRepository.save(item);
        return savedItem;
    }
    
    @DeleteMapping("/{centreId}/{itemId}")
    public ResponseEntity<Void> deleteItemFromCentre(@PathVariable int centreId, @PathVariable int itemId) {
        Optional<DistributionCentre> optionalDistributionCentre = distributionCentreRepository.findById(centreId);
        if (optionalDistributionCentre.isPresent()) {
            DistributionCentre distributionCentre = optionalDistributionCentre.get();
            Optional<ItemModel> optionalItem = itemRepository.findById(itemId);
            if (optionalItem.isPresent()) {
                ItemModel item = optionalItem.get();
                if (item.getDistributionCentre().equals(distributionCentre)) {
                    itemRepository.delete(item);
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.badRequest().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @GetMapping("/{id}/brand/{brand}")
    public ResponseEntity<List<ItemModel>> getItemsByBrandForCentre(@PathVariable int id, @PathVariable String brand) {
        Optional<DistributionCentre> optionalDistributionCentre = distributionCentreRepository.findById(id);
        if (optionalDistributionCentre.isPresent()) {
            DistributionCentre distributionCentre = optionalDistributionCentre.get();
            List<ItemModel> items = distributionCentre.getItem();
            List<ItemModel> itemsByBrand = new ArrayList<>();
            for (ItemModel item : items) {
                if (item.getBrand().toString().equalsIgnoreCase(brand)) {
                    itemsByBrand.add(item);
                }
            }
            if (itemsByBrand.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(itemsByBrand);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @GetMapping("/{id}/name/{name}")
    public ResponseEntity<List<ItemModel>> getItemsByNameForCentre(@PathVariable int id, @PathVariable String name) {
        Optional<DistributionCentre> optionalDistributionCentre = distributionCentreRepository.findById(id);
        if (optionalDistributionCentre.isPresent()) {
            DistributionCentre distributionCentre = optionalDistributionCentre.get();
            List<ItemModel> items = distributionCentre.getItem();
            List<ItemModel> itemsByName = new ArrayList<>();
            for (ItemModel item : items) {
                if (item.getName().toString().equalsIgnoreCase(name)) {
                    itemsByName.add(item);
                }
            }
            if (itemsByName.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(itemsByName);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
