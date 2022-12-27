package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StringTest {

    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void splitTest() {
        String[] actual = "1,2".split(",");
        assertThat(actual).contains("1", "2");
    }

    @Test
    void oneCharSplitTest() {
        String[] actual = "1".split(",");
        assertThat(actual).contains("1");
    }

    @Test
    void parenthesisRemoveTest() {
        String str = "\"(1,2)\"";
        int openingP = str.indexOf("(");
        int closingP = str.lastIndexOf(")");
        String actual = str.substring(0, openingP) + str.substring(openingP + 1, closingP) + str.substring(closingP + 1);
        assertThat(actual).isEqualTo("\"1,2\"");
    }

    @DisplayName("charAt() exception test")
    @Test
    void findCharExceptionTest() {
        String str1 = "abc";
        int input = 99;

        assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
                .isThrownBy(() -> {
                    throw new StringIndexOutOfBoundsException(str1.charAt(input));
                }).withMessageMatching("String index out of range: 99");

    }

}
