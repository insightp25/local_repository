package com.example.sample;

public class CalculationRequest {
    private final long num1;
    private final long num2;
    private final String operator;

    public CalculationRequest(String[] strings) {
        if (strings.length != 3) {
            throw new BadRequestException();
        }

        String operator = strings[1];

        if (operator.length() != 1 || isInvalidOperator(operator)) {
            throw new InvalidOperatorException();
        }

        this.num1 = Long.parseLong(strings[0]);
        this.num2 = Long.parseLong(strings[2]);
        this.operator = operator;
    }

    private static boolean isInvalidOperator(String s) {
        return !s.equals("+") &&
                !s.equals("-") &&
                !s.equals("*") &&
                !s.equals("/");
    }

    public long getNum1() {
        return num1;
    }

    public long getNum2() {
        return num2;
    }

    public String getOperator() {
        return operator;
    }
}
