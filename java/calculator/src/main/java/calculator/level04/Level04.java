package calculator.level04;

import calculator.level03.calculate.Calculator;
import calculator.level04.validation.ValidInput;

import java.util.Scanner;

import static calculator.enums.SystemMessage.*;

// 모든 Level 의 요구사항에는 없지만 개인적으로 필요하다 생각한 부분을 반영한 Level04 계산기 클래스
public class Level04 {

    // level03/calculate/Calculator 객체 재사용
    private final Calculator<Double> cal = new Calculator<>();

    // Exception 을 활용해 추가한 level04/validation/ValidInput 객체 사용
    private final ValidInput valid = new ValidInput();

    // Scanner 객체를 파라미터로 전달 받아 계산기 동작을 수행하는 메서드
    // 기본적으로 수행을 반복하도록 true 를 반환, 수행 종료시 false 반환
    public boolean start(Scanner sc) {

        String input = sc.nextLine();

        // 입력 값과 종료 명령어가 같다면 false 반환, 계산기 반복 호출을 멈춤
        if (valid.validExit(input)) return false;

        // 입력 값의 유효성을 검증해 유효하지 않다면 예외가 발생, 발생한 예외를 처리
        // 예외처리 후 true 를 반환해 계산기 처음으로 돌아가 다시 입력부터 시작
        try {
            valid.validCalculation(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }

        // 연산자, 피연산자 Calculator 객체 필드에 저장
        setCalculation(input);

        // 연산 수행 -> 최근 연산 결과(최대 5개) 출력 -> 현재 연산결과 보다 큰 최근 연산결과 출력
        double result = cal.calculate();   // 연산 수행
        printResult(result);        // 연산 결과 출력
        recentlyResults();          // 최근 연산 결과(최대 5개, 최근 순)
        bigRecentlyResults(result);    // 현재 연산 결과보다 큰 최근 연산 결과들 출력
        cal.saveResult(result);     // 현재 연산 결과 Calculator 객체의 Collection 필드에 저장

        return true;
    }

    private void bigRecentlyResults(double result) {
        System.out.println(
                BIGGER_RESULTS.getMessage() + cal.getBiggerResults(result) + "\n"
                + RESULT_WINDOW_DIVIDER.getMessage()
        );
    }

    private void recentlyResults() {
        System.out.println(
                RECENTLY_RESULTS.getMessage() + cal.getCalculateResults()
        );
    }

    private void printResult(double result) {
        System.out.println(CALCULATE_RESULT.getMessage() + result);
    }

    private void setCalculation(String input) {
        String[] operation = input.split(" ");
        cal.setFirstNum(Double.parseDouble(operation[0]));
        cal.setOperation(operation[1]);
        cal.setSecondNum(Double.parseDouble(operation[2]));
    }

}
