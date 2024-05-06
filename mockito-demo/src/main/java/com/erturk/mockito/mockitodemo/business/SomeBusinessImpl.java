package com.erturk.mockito.mockitodemo.business;

public class SomeBusinessImpl {
    private DataService dataService;

    public SomeBusinessImpl(DataService dataService) {
        super();
        this.dataService = dataService;
    }

    public int findGreatestFromAllData() {
        int[] data = dataService.retreiveAllData();
        int greatesValue = Integer.MIN_VALUE;
        for (int value: data) {
            if (value > greatesValue) {
                greatesValue = value;
            }
        }
        return greatesValue;
    }
}

interface DataService {
    int[] retreiveAllData();
}