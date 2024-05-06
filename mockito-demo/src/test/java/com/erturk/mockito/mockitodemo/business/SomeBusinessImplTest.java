package com.erturk.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SomeBusinessImplTest {
    @Test
    void test() {
        DataServiceStub stub = new DataServiceStub();
        SomeBusinessImpl businessImpl = new SomeBusinessImpl(stub);
        assertEquals(453, businessImpl.findGreatestFromAllData());
    }
}

class DataServiceStub implements DataService {

    @Override
    public int[] retreiveAllData() {
        return new int[] {12,423,123,453,122};
    }
}