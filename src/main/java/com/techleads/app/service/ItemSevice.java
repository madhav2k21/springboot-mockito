package com.techleads.app.service;

import com.techleads.app.exception.ItemNotFoundException;
import com.techleads.app.model.Item;
import com.techleads.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class ItemSevice {
    @Autowired
    private ItemRepository itemRepository;

    public Item findItems(){
        Item item = Item.builder().id(101)
                .itemName("Pen")
                .qty(1)
                .price(20.0).build();
        return  item ;
    }

    public List<Item> findAllItems(){
        List<Item> items = itemRepository.findAll();
        Consumer<Item> price=item -> item.setValue(item.getPrice()*item.getQty());
        items.forEach(getPrice());

       return items;
    }

    public Item findItemById(Integer id){
        Optional<Item> byId = itemRepository.findById(id);
        byId.ifPresent(getPrice());
        return byId.orElseThrow(()->new ItemNotFoundException("Item not found"));
    }

    private Consumer<Item> getPrice() {
        return item -> item.setValue(item.getPrice()*item.getQty());
    }


}
