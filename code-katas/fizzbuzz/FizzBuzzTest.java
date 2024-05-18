import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FizzBuzzTest {
    private FizzBuzz fizzBuzz;

    @BeforeEach
    public void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    public void testNumber() {
        assertEquals("1", fizzBuzz.evaluate(1));
        assertEquals("2", fizzBuzz.evaluate(2));
    }

    @Test
    public void testFizz() {
        assertEquals("Fizz", fizzBuzz.evaluate(3));
        assertEquals("Fizz", fizzBuzz.evaluate(6));
    }

    @Test
    public void testBuzz() {
        assertEquals("Buzz", fizzBuzz.evaluate(5));
        assertEquals("Buzz", fizzBuzz.evaluate(10));
    }

    @Test
    public void testFizzBuzz() {
        assertEquals("FizzBuzz", fizzBuzz.evaluate(15));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(30));
    }
}
