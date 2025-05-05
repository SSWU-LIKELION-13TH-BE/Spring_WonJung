package com.likelion.session.service.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    // 두 숫자의 합을 구하는 메서드
    public int add(int number1, int number2) {
        return number1 + number2;
    }

    // 두 숫자의 곱을 구하는 메서드
    public int multiply(int number1, int number2) {
        return number1 * number2;
    }

    public int substract(int number1, int number2)  {
        return number1 - number2;
    }

    public int divide(int number1, int number2) {
        return number1 / number2;
    }
}
