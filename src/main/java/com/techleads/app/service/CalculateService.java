package com.techleads.app.service;

import java.util.Arrays;
import java.util.OptionalInt;

public class CalculateService {

    private DataService dataService;

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public int calculateSum(int[] data){
        int sum = Arrays.stream(data).reduce(0, (a, b) -> a + b);
        return sum;

    }

    public int calculateSumFromDataService(){
        int[] data = dataService.retrieveAllData();
        int sum = Arrays.stream(data).reduce(0, (a, b) -> a + b);
        return sum;

    }
}
