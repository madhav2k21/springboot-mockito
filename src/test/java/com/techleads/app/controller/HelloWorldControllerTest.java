package com.techleads.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {HelloWorldController.class})
class HelloWorldControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHelloWorld() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello").contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc
                .perform(requestBuilder)
                .andExpect(status()
                .isOk())
                .andExpect(content().string("Hello World"))
                .andReturn();
        assertEquals("Hello World",mvcResult.getResponse().getContentAsString());

    }
}