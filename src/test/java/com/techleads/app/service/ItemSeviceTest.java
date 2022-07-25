package com.techleads.app.service;

import com.techleads.app.exception.ItemNotFoundException;
import com.techleads.app.model.Item;
import com.techleads.app.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ItemSeviceTest {

    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private ItemSevice itemSevice;
    @Test
    void testFindAllItems() {
        List<Item> items = items();
        when(itemRepository.findAll()).thenReturn(items);
        List<Item> allItems = itemSevice.findAllItems();
        assertThat(allItems.size()).isEqualTo(items.size());
        assertIterableEquals(allItems, items);

    }

    @Test
    void testFindAllItemsZero() {
        List<Item> items = new ArrayList<>();
        when(itemRepository.findAll()).thenReturn(items);
        List<Item> allItems = itemSevice.findAllItems();
        assertThat(allItems.size()).isEqualTo(items.size());
        assertIterableEquals(allItems, items);

    }

    @Test
    void testFindItemById() {
        Item item = items().get(0);
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        Item itemById = itemSevice.findItemById(1);

        assertThat(itemById.getId()).isEqualTo(item.getId());
        assertThat(itemById.getItemName()).isEqualTo(item.getItemName());
        assertThat(itemById.getPrice()).isEqualTo(item.getPrice());
        assertThat(itemById.getQty()).isEqualTo(item.getQty());
        assertThat(itemById.getValue()).isEqualTo(item.getValue());
    }

    @Test
    void testFindItemByIdThrowItemNotFoundException() {
        when(itemRepository.findById(anyInt())).thenThrow(new ItemNotFoundException("Item not found"));
        ItemNotFoundException itemNotFoundException = assertThrows(ItemNotFoundException.class, () -> {

            itemSevice.findItemById(1);
        });

        assertThat(itemNotFoundException.getMessage()).isEqualTo("Item not found");
        assertThat(ItemNotFoundException.class).isEqualTo(itemNotFoundException.getClass());

    }


   private List<Item> items(){
       List<Item> items = Arrays.asList(
               new Item(1, "pen", 1, 12.3),
               new Item(2, "book", 1, 22.3),
               new Item(3, "mouse", 1, 340.24),
               new Item(4, "laptop", 1, 560.9)
       );

       items.forEach(item-> item.setValue(item.getPrice()*item.getQty()));
       return items;
   }
}