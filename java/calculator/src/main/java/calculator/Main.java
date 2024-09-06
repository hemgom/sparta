package calculator;

import calculator.level02.Level02;
import calculator.level03.Level03;

import java.util.Scanner;
import java.util.regex.Pattern;

import static calculator.level03.enums.SystemMessage.*;
import static calculator.level03.enums.ValidCriteria.*;

public class Main {
    public static void main(String[] args) {
        // 값 입력을 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 사용 가능한 계산기 목록을 사용자에게 알리는 메시지
        String calculatorNum;
        boolean useOrNot = true;
        System.out.println(SELECT_CALCULATOR.getMessage()); // level03 계산기에도 사용되는 로직이기에 enum 클래스를 사용
        while (true) {
            calculatorNum = sc.nextLine();
            if (checkExit(calculatorNum)) {
                useOrNot = false;
                break;  // "exit" 입력시 반복탈출, 계산기 종료
            }
            if (!checkCalculatorNum(calculatorNum)) break; // 항목에 포함된 번호가 아니면 계산기 선택 반복
        }

        // 계산기 입력 방식을 사용자에게 알리는 메시지, 계산기 실행시에만 출력됨
        if (useOrNot) {
            System.out.println(INPUT_GUIDE.getMessage());   // level03 계산기에도 사용되는 로직이기에 enum 클래스를 사용
        }

        // Level 03 계산기
        if (calculatorNum.equals(LEVEL_THREE.getCriteria())) {
            Level03 lv3Calculator = new Level03();
            while (true) {
                if (!lv3Calculator.start(sc)) break;
            }
        }

        // Level 02 계산기
        if (calculatorNum.equals(LEVEL_TWO.getCriteria())) {
            Level02 lv2Calculator = new Level02();
            lv2Calculator.start(sc);
            lv2Calculator.currentResults();
        }

        // Level 01 계산기
        while (calculatorNum.equals(LEVEL_ONE.getCriteria())) {

            // 연산에 필요한 값 입력 및 변수 저장 및 사용자의 계산기 수행 의사확인 과정
            // 입력 값 저장 후 바로 저장 값이 "exit"(종료 명령어)인지 확인하는 메서드 호출
            String firstInput = sc.nextLine();
            if (checkExit(firstInput)) break;
            String operator = sc.nextLine();
            if (checkExit(operator)) break;
            String secondInput = sc.nextLine();
            if (checkExit(secondInput)) break;

            // 입력 값 유효성 검사, 유효하지 않은 값이 있다면 반복문 처음으로
            if (!validInputCheck(firstInput, operator, secondInput)) continue;

            // 나누기 연산을 염두해 입력받은 정수들을 실수형으로 변환, operator 는 그대로 사용
            double firstNum = Double.parseDouble(firstInput);
            double secondNum = Double.parseDouble(secondInput);

            // 사칙연산 수행, 입력받은 연산기호에 맞춰 연산 메서드를 수행후 결과 출력
            switch (operator) {
                case "+":
                    addOperation(firstNum, secondNum);
                    break;
                case "-":
                    subOperation(firstNum, secondNum);
                    break;
                case "*":
                    mulOperation(firstNum, secondNum);
                    break;
                case "/":
                    divOperation(firstNum, secondNum);
                    break;
            }

            System.out.println("===========================");  // 실행창 수행연산 구분선

        }

        sc.close();     // 입력 종료
        System.out.println(CALCULATOR_EXIT.getMessage());
        System.exit(0); // main 메서드 실행 (정상)종료 지정
    }

    // 입력한 계산기 번호의 유효성을 검사하는 메서드
    // 유효성 통과시 false, 통과 실패시 true 반환
    public static boolean checkCalculatorNum(String num) {
        if (!Pattern.matches(CALCULATOR_NUMBER.getCriteria(), num)) {  // level03 계산기에도 사용되는 로직이기에 enum 클래스를 사용
            System.out.println(
                    ERROR_SELECT_CALCULATOR.getMessage() + "\n" +
                    RESULT_WINDOW_DIVIDER.getMessage()
            );
            return true;
        }
        return false;
    }

    // 연산결과를 출력하는 메서드
    public static void printResult(double result) {
        System.out.println("연산 결과 : " + result);
    }

    // 사칙연산 중 하나를 수행하는 메서드
    public static void addOperation(double firstNum, double secondNum) {
        printResult(firstNum + secondNum);
    }
    public static void subOperation(double firstNum, double secondNum) {
        printResult(firstNum - secondNum);
    }
    public static void mulOperation(double firstNum, double secondNum) {
        printResult(firstNum * secondNum);
    }
    public static void divOperation(double firstNum, double secondNum) {
        printResult(firstNum / secondNum);
    }

    /**
     * 입력 값들의 유효성을 한 번에 확인하는 메서드
     * 오류가 있는 입력 값에 대한 수정 요청 메시지를 출력
     * 반환 값(result)이 false 라면 처음부터 다시 입력해달라는 요청 메시지까지 출력
     * 결과적으로 유효하다면 true, 유효하지 않다면 false 반환
     */
    public static boolean validInputCheck(String firstNum, String operator, String secondNum) {
        final String NUMBER = "^[0-9]$";
        final String OPERATOR = "[+\\-*/]";

        boolean result = true;
        if (!Pattern.matches(NUMBER, firstNum) || !Pattern.matches(NUMBER, secondNum)) {
            System.out.println("피연산자 입력오류 : 입력 가능한 피연산자는 '0'과 자연수입니다.");
            result = false;
        }
        if (!Pattern.matches(OPERATOR, operator)) {
            System.out.println("연산자 입력오류 : 입력 가능한 연산자는 '+', '-', '*', '.' 입니다.");
            result = false;
        }
        if (!result) {
            System.out.println(
                    "처음부터 다시 입력 부탁드립니다.\n" +
                    "==========================="
            );
        }

        return result;
    }

    // 입력받은 값이 계산기 종료 값과 같은지 확인하는 메서드
    // 계산기 종료 명령어와 같다면 'true', 다르다면 'false' 반환
    public static boolean checkExit(String input) {
        final String exitStr = "exit";
        return input.equals(exitStr);
    }
}