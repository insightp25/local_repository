package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int meterCount = getMeterCriteriaCount(s);

        if (meterCount <= 1) return PasswordStrength.WEAK;
        if (meterCount == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMeterCriteriaCount(String s) {
        int meterCount = 0;

        if (s.length() >= 8) meterCount++;
        if (meetsContainingNumberCriteria(s)) meterCount++;
        if (meetsContainingUppercaseCriteria(s)) meterCount++;

        return meterCount;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
