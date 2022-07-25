package com.techleads.app.controller;

import com.techleads.app.model.Item;
import com.techleads.app.service.ItemSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemSevice itemSevice;
    @GetMapping(value = {"/items1"})
    public Item findItems(){
        return itemSevice.findItems();
    }

    @GetMapping(value = "/items")
    public ResponseEntity<List<Item>> findAllItems(){
        List<Item> allItems = itemSevice.findAllItems();
        return ResponseEntity.ok(allItems);
    }

    @GetMapping(value = "/items/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable("id") Integer id){
        Item itemById = itemSevice.findItemById(id);
        return new ResponseEntity<>(itemById, HttpStatus.OK);
    }
}
