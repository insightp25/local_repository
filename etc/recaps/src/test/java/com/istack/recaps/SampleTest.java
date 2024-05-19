package com.istack.recaps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SampleTest {
    @Test
    @DisplayName("더하기 테스트")
    void test1() {
        Sample sample = new Sample();
        Double result = sample.operate(8, "+", 2);
        System.out.println("result = " + result);

        Assertions.assertEquals(10, result);
    }

    @Test
    @DisplayName("나누기 테스트")
    void test2() {
        Sample calculator = new Sample();
        Double result = calculator.operate(8, "/", 2);
        System.out.println("result = " + result);

        Assertions.assertEquals(4, result);
    }
}