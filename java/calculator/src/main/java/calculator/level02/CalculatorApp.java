package calculator.level02;

import calculator.level02.calculate.Calculator;
import calculator.level02.validation.ValidInputData;

import java.util.Queue;
import java.util.Scanner;

// 의존관계주입을 사용해 구현한 계산기 수행 동작을 구현한 클래스
public class CalculatorApp {
    private final ValidInputData valid = new ValidInputData();
    private final Calculator cal = new Calculator();

    public void start(Scanner sc) {

        while (true) {

            // 연산에 필요한 값 입력 및 변수 저장 및 사용자의 계산기 수행 의사확인 과정
            // 입력 값 저장 후 바로 저장 값이 "exit"(종료 명령어)인지 확인하는 메서드 호출
            String firstInput = sc.nextLine();
            if (valid.validExit(firstInput)) break;
            String operator = sc.nextLine();
            if (valid.validExit(operator)) break;
            String secondInput = sc.nextLine();
            if (valid.validExit(secondInput)) break;

            // 입력 값 유효성 검사, 유효하지 않은 값이 있다면 반복문 처음으로
            if (valid.validInputData(firstInput, operator, secondInput)) continue;

            // 입력 값을 Calculator 객체에 저장
            cal.setFirstNum(firstInput);
            cal.setSecondNum(secondInput);
            cal.setOperation(operator);

            double result = cal.calculate();    // 연산 수행
            cal.saveResult(result);             // 연산 결과 저장
            System.out.println("연산 결과 : " + result + "\n===========================");

        }

    }

    // 최근 연산한 결과(최대 5개)를 출력하는 메서드
    public void currentResults() {
        System.out.println(
                "===========================\n최근 연산 결과 : " + cal.getCalculateResults() + "\n==========================="
        );
    }
}
