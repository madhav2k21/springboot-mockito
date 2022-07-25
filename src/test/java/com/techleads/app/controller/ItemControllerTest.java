package com.techleads.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techleads.app.model.Item;
import com.techleads.app.service.ItemSevice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = {ItemController.class})
//@Import(value = {ItemSevice.class}) //works
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;
    //    @Mock //does not work
    @MockBean //works
    private ItemSevice itemSevice;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findItems() throws Exception {

        when(itemSevice.findItems()).thenReturn(Item.builder().id(101)
                .itemName("book")
                .qty(1)
                .price(20.0).build());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/items1").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json("{   id: 101,  itemName: book,   qty: 1,  price: 20.0 }"))
                .andReturn();

    }

    @Test
    void testFindAllItems() throws Exception {
        List<Item> items = items();
        when(itemSevice.findAllItems()).thenReturn(items);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/items").contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(content().json(objectMapper.writeValueAsString(items)))
                .andReturn();

        Assertions.assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.OK.value());
    }

    @Test
    void testFindItemById() throws Exception {
        Item item = items().get(0);
        Integer id = 1;
        when(itemSevice.findItemById(anyInt())).thenReturn(item);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/{id}", id).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(item.getId())))
                .andExpect(jsonPath("$.itemName", is(item.getItemName())))
                .andExpect(jsonPath("$.price", is(item.getPrice())))
                .andExpect(jsonPath("$.qty", is(item.getQty())))
                .andExpect(jsonPath("$.value", is(item.getValue())))
                .andExpect(content().json(objectMapper.writeValueAsString(item)))
                .andReturn();
    }

    private List<Item> items() {
        List<Item> items = Arrays.asList(
                new Item(1, "pen", 1, 12.3),
                new Item(2, "book", 1, 22.3),
                new Item(3, "mouse", 1, 340.24),
                new Item(4, "laptop", 1, 560.9)
        );

        items.forEach(item -> item.setValue(item.getPrice() * item.getQty()));
        return items;
    }


}