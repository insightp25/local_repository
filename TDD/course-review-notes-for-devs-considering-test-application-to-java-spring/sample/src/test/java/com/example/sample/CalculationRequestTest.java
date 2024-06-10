package com.example.sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculationRequestTest {

    @Test
    void able_to_parse_valid_number() {
        // given
        String[] strings = {"2", "+", "3"};
        // when
        CalculationRequest request = new CalculationRequest(strings);

        // then
        assertEquals(2, request.getNum1());
        assertEquals("+", request.getOperator());
        assertEquals(3, request.getNum2());
    }

    @Test
    void able_to_parse_valid_number_with_3_digits() {
        // given
        String[] strings = {"222", "+", "333"};
        // when
        CalculationRequest request = new CalculationRequest(strings);

        // then
        assertEquals(222, request.getNum1());
        assertEquals("+", request.getOperator());
        assertEquals(333, request.getNum2());
    }

    @Test
    void throws_exception_when_input_number_with_invalid_input_length() {
        // given
        String[] strings = {"222", "+"};

        // when & then
        assertThrows(BadRequestException.class, () -> {
            new CalculationRequest(strings);
        });
    }

    @Test
    void throws_exception_when_input_invalid_opeator() {
        // given
        String[] strings = {"222", "x", "333"};

        // when & then
        assertThrows(InvalidOperatorException.class, () -> {
            new CalculationRequest(strings);
        });
    }

    @Test
    void throws_exception_when_input_operator_with_invalid_length() {
        // given
        String[] strings = {"222", "+-*/", "333"};

        // when & then
        assertThrows(InvalidOperatorException.class, () -> {
            new CalculationRequest(strings);
        });
    }
}
