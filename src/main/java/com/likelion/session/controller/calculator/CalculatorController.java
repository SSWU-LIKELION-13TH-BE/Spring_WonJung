package com.likelion.session.controller.calculator;

import com.likelion.session.dto.calculator.request.CalculatorAddRequest;
import com.likelion.session.dto.calculator.request.CalculatorMultiplyRequest;
import com.likelion.session.service.calculator.CalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    // 생성자 주입 (Constructor Injection)
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return calculatorService.add(request.getNumber1(), request.getNumber2());
    }
//    // DTO 사용 -> 객체 바인딩
//    public int addTwoNumbers(CalculatorAddRequest request) {
//        return request.getNumber1() + request.getNumber2();
//    }

    // 쿼리 파라미터 사용
//    public int addTwoNumbers(
//            @RequestParam int number1,
//            @RequestParam int number2
//    ) {
//       return number1 + number2;
//    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }

}