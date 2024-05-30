package chap03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {
    PasswordStrengthMeter3 meter = new PasswordStrengthMeter3();

    @Test
    void meetsOnlyUpperCriteria_Then_Weak() {
        PasswordStrengthMeter3 meter = new PasswordStrengthMeter3();
        PasswordStrength3 result = meter.meter("abcDef");
        assertEquals(PasswordStrength3.WEAK, result);
    }

    @Test
    void meetsAllCriteria_Then_Weak() {
        PasswordStrengthMeter3 meter = new PasswordStrengthMeter3();
        PasswordStrength3 result = meter.meter("abcDef12");
        assertEquals(PasswordStrength3.STRONG, result);

        PasswordStrength3 result2 = meter.meter("sZcDef123");
        assertEquals(PasswordStrength3.STRONG, result2);
    }

    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        PasswordStrengthMeter3 meter = new PasswordStrengthMeter3();
        PasswordStrength3 result = meter.meter("a2!@A");
        assertEquals(PasswordStrength3.NORMAL, result);

        PasswordStrength3 result2 = meter.meter("Ab2!c");
        assertEquals(PasswordStrength3.NORMAL, result2);
    }
}
