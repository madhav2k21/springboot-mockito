package com.techleads.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateServiceTest {
    @Mock
    private DataService dataServiceMock;
    @InjectMocks
    private CalculateService underTestCalculateService;

    @Test
    void testCalculateSum() {
        int sum = underTestCalculateService.calculateSum(new int[]{1, 2, 3});
        assertEquals(6,sum);
    }

    @Test
    void testCalculateSumWithEmpty() {
        int sum = underTestCalculateService.calculateSum(new int[]{});
        assertEquals(0,sum);
    }

    @Test
    void testCalculateSumWithOneValue() {
        int sum = underTestCalculateService.calculateSum(new int[]{5});
        assertEquals(5,sum);
    }

    @Test
    void testCalculateSumUsingDataService() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1,2,3});

        int sum = underTestCalculateService.calculateSumFromDataService();
        assertEquals(6,sum);
    }


    @Test
    void testCalculateSumWithEmptyUsingDataService() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        int sum = underTestCalculateService.calculateSumFromDataService();
        assertEquals(0,sum);
    }

    @Test
    void testCalculateSumWithOneValueUsingDataService() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{5});
        int sum = underTestCalculateService.calculateSumFromDataService();
        assertEquals(5,sum);
    }
}