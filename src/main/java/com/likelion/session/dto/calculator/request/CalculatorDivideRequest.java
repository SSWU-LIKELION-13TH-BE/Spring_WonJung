package com.likelion.session.dto.calculator.request;

public class CalculatorDivideRequest {

    private final int number1;
    private final int number2;

    public CalculatorDivideRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}
