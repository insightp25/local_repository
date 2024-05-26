package chap03;

public class PasswordStrengthMeter3 {
    public PasswordStrength3 meter(String s) {
        if (s.length() < 8) return PasswordStrength3.NORMAL;

        return PasswordStrength3.STRONG;
    }
}
