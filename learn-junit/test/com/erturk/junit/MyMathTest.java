package com.erturk.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyMathTest {
    private MyMath math = new MyMath();
    @Test
    void test() {
        assertEquals(6, math.calculateSum(new int[] {1,2,3}));
        assertEquals(0, math.calculateSum(new int[] {}));
    }
}
