package com.techleads.app.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AssertJTest {

    @Test
    void learning() {
        List<Integer> numbers = Arrays.asList(12, 15, 45);

       assertThat(numbers).hasSize(3);
       assertThat(numbers).contains(15,45);
       assertThat(numbers).allMatch(x->x>10);
       assertThat("").isEmpty();
       assertThat("ABCDEF").contains("DEF");

        assertThat("ABCDEF").startsWith("AB");
        assertThat("ABCDEF").endsWith("DEF");


    }
}
