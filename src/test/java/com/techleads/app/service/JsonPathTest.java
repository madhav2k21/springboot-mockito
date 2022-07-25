package com.techleads.app.service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPathTest {
    private String responseFromService="[" +
            "{\"id\":101,\"name\":\"pen\",\"quantity\":10}," +
            "{\"id\":102,\"name\":\"book\",\"quantity\":10}," +
            "{\"id\":103,\"name\":\"Eraser\",\"quantity\":14}" +
            "]";
    private DocumentContext documentContext;
    @BeforeEach
    void setup(){
       documentContext = JsonPath.parse(responseFromService);
    }
    @Test
    void learning(){

        Integer length = documentContext.read("$.length()");
        assertThat(length).isEqualTo(3);
    }

    @Test
    void readAllIds(){
        List<Integer> ids = documentContext.read("$..id");
        assertThat(ids).containsExactly(101,102,103);
    }

    @Test
    void readFromIndex(){
        String secondObject = documentContext.read("$.[1]").toString();
        assertThat(secondObject).isEqualTo("{id=102, name=book, quantity=10}");

        String firstObject = documentContext.read("$.[0:1]").toString();
        assertThat(firstObject).isEqualTo("[{\"id\":101,\"name\":\"pen\",\"quantity\":10}]");

        String s = documentContext.read("$.[?(@.name=='Eraser')]").toString();
        assertThat(s).isEqualTo("[{\"id\":103,\"name\":\"Eraser\",\"quantity\":14}]");

    }
}
