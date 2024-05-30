package com.istack.recaps;

public class Sample {
    public Double operate(double num1, String op, double num2) {
        return switch (op) {
            case "*" -> num1 * num2;
            case "/" -> num2 != 0 ? num1 / num2 : null;
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            default -> throw new IllegalArgumentException("잘못된 연산자입니다.");
        };
    }
}