import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {
    static StringCalculator strCalc;
    static String errMsg = "계산식을 입력해주세요.";

    @BeforeAll
    static void initAll() {
        strCalc = new StringCalculator();
    }
    @Test
    void StrCalcTest() {
        String input = "1 + 2 + 3";

        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        strCalc.getValue();

        assertThat("6").isEqualTo(out.toString());

    }
}
