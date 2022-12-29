package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(9);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    void sizeTest() {
        int size = numbers.size();
        assertThat(size).isEqualTo(4);
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 9})
    void contains(int input) {
        assertThat(numbers.contains(input)).isTrue();
    }


    @ParameterizedTest
    @CsvSource({"1, true", "2, true", "3, true", "9, true", "4, false", "5, false"})
    void contains(int input, String expected) {
        assertThat(numbers.contains(input) ? "true" : "false").isEqualTo(expected);
    }

}
