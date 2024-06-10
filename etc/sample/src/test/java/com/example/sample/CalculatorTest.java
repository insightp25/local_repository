package com.example.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    void can_operate_addition() {
        // given
        long num1 = 2;
        String operator = "+";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when
        long result = calculator.calculate(num1, operator, num2);

        // then
        assertEquals(5, result); // junit assertion
        assertThat(result).isEqualTo(5); // assertj assertion
    }

    @Test
    void can_operate_subtraction() {
        // given
        long num1 = 5;
        String operator = "-";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when
        long result = calculator.calculate(num1, operator, num2);

        // then
        assertEquals(2, result); // junit assertion
        assertThat(result).isEqualTo(2); // assertj assertion
    }

    @Test
    void can_operate_production() {
        // given
        long num1 = 2;
        String operator = "*";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when
        long result = calculator.calculate(num1, operator, num2);

        // then
        assertEquals(6, result); // junit assertion
        assertThat(result).isEqualTo(6); // assertj assertion
    }

    @Test
    void can_operate_division() {
        // given
        long num1 = 6;
        String operator = "/";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when
        long result = calculator.calculate(num1, operator, num2);

        // then
        assertEquals(2, result); // junit assertion
        assertThat(result).isEqualTo(2); // assertj assertion
    }

    @Test
    void throws_exception_when_input_wrong_operator() {
        // given
        long num1 = 2;
        String operator = "www";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when & then
        assertThrows(InvalidOperatorException.class, () -> {
            calculator.calculate(num1, operator, num2);
        });
    }
}
