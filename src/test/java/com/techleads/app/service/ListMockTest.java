package com.techleads.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListMockTest {

    @Mock
    private List mockList;
    @Spy
    private ArrayList listSpy;

    @Test
    void testMockList() {
        when(mockList.size()).thenReturn(5);
        assertEquals(5, mockList.size());
    }

    @Test
    void testMockListReturnWithDifferentValues() {
        when(mockList.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mockList.size());
        assertEquals(10, mockList.size());
    }

    @Test
    void testListMockWithParams() {
        when(mockList.get(0)).thenReturn("one");
        assertEquals("one", mockList.get(0));
        assertEquals(null, mockList.get(1));
    }

    @Test
    void testListReturnGenericValueUsingAny() {
        when(mockList.get(anyInt())).thenReturn("admin");
        assertEquals("admin", mockList.get(0));
        assertEquals("admin", mockList.get(1));
        verify(mockList, times(2)).get(anyInt());
    }

    @Test
    void testListMockVerifyCall() {
        when(mockList.get(0)).thenReturn(1);
        mockList.get(0);
        verify(mockList, times(1)).get(0);
        verify(mockList, never()).get(2);
    }

    @Test
    void argumentCapturing() {
        mockList.add("one");
        //verification

        verify(mockList, times(1)).add("one");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockList).add(captor.capture());

        assertEquals("one", captor.getValue());
    }

    @Test
    void testMultipleArgumentCapturing(){
        mockList.add("one");
        mockList.add("two");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockList, times(2)).add(captor.capture());
        assertEquals("one", captor.getAllValues().get(0));
        assertEquals("two", captor.getAllValues().get(1));
    }
    @Test
    void testMock(){
        System.out.println(mockList.get(0)); //null
        System.out.println(mockList.size()); //0
        mockList.add("one");
        mockList.add("two");
        System.out.println(mockList.size()); //0
        when(mockList.size()).thenReturn(5);
        System.out.println(mockList.size()); //5
    }

    @Test
    void testSpying(){
//        System.out.println(listSpy.get(0)); //NPE
        System.out.println(listSpy.size()); //0
        listSpy.add("one");
        listSpy.add("two");
        System.out.println(listSpy.size()); //2
        when(listSpy.size()).thenReturn(5);
        System.out.println(listSpy.size()); //5
        listSpy.add("three");
        System.out.println(listSpy.size()); //5

        verify(listSpy, times(1)).add("one");
        verify(listSpy, times(4)).size();
    }

}