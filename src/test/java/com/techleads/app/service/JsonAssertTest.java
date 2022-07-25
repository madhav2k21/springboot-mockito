package com.techleads.app.service;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {
    String actualResponse="{   \"id\": 101,   \"itemName\": \"Pen\",   \"qty\": 1,   \"price\": 20.0 }";
    String stricExpectedResponse="{   \"id\": 101,   \"itemName\": \"Pen\",   \"qty\": 1,   \"price\": 20.0 }";
    String stricFalseExpectedResponse="{   \"id\": 101,   \"itemName\": \"Pen\",   \"qty\": 1 }";

    String getActualResponseNoQuotes="{   id: 101,   itemName: Pen,   qty: 1,   price: 20.0 }";

    @Test
    void jsonAssertStricTrue() throws JSONException {
        JSONAssert.assertEquals(stricExpectedResponse,actualResponse, true);

    }

    @Test
    void jsonAssertStricFalse() throws JSONException {
        JSONAssert.assertEquals(stricFalseExpectedResponse,actualResponse, false);

    }

    @Test
    void jsonAssertWithoutQuotes() throws JSONException {
        JSONAssert.assertEquals(getActualResponseNoQuotes,getActualResponseNoQuotes, true);

    }
}
