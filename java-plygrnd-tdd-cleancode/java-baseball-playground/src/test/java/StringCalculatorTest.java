import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {
    StringCalculator strCalc;

    @BeforeAll
    void initAll() {
        strCalc = new StringCalculator();
    }
    @Test
    void StrCalcTest() {
        int num = strCalc.getValue();
        assertThat(5).isEqualTo(num);

    }
}
