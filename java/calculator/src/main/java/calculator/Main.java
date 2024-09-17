package calculator;

import calculator.level02.Level02;
import calculator.level03.Level03;
import calculator.level04.Level04;

import java.util.Scanner;
import java.util.regex.Pattern;

import static calculator.enums.SystemMessage.*;
import static calculator.enums.ValidCriteria.*;

/**
 * 계산기(프로그램) 시작 클래스(= main 메서드)
 * 계산기를 'Level' 에 따라 구현
 * 특정 레벨의 계산기가 사용하는 로직에 대해서는 해당 레벨의 계산기의 클래스, 메서드 등을 적용
 */
public class Main {
    public static void main(String[] args) {

        //값을 입력받기 위해 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 사용자가 계산기를 선택하는 과정을 작성한 로직
        // 사용자는 계산기에 해당하는 번호 정보를 확인 할 수 있고, 입력한 값에 따라 해당하는 계산기가 사용됨
        String calNum;
        boolean useOrNot = true;   // 계산기 사용 여부를 갖는 변수, true = 사용 | false = 사용 안 함
        System.out.println(SELECT_CALCULATOR.getMessage());   // 사용 가능한 계산기 목록을 포함한 게산기 선택 안내 메시지 출력
        
        while (true) {
            calNum = sc.nextLine();
            if (calNum.equals(EXIT_COMMAND.getCriteria())) {
                useOrNot = false;
                break;   // "exit" 입력시 반복 탈출, 계산기 종료
            }
            if (isValidCalculatorNumber(calNum)) break;   // 오입력 시 과정 반복, 정상입력 시 반복 탈출
        }

        // 계산기를 사용할 경우 사용자에게 입력 방식 안내
        if (useOrNot) {
            if (calNum.equals(LEVEL_FOUR.getCriteria())) {
                System.out.println(ONE_INPUT_GUIDE.getMessage());
            } else {
                System.out.println(THREE_INPUT_GUIDE.getMessage());
            }
        }

        // Level04 계산기 동작
        if (calNum.equals(LEVEL_FOUR.getCriteria())) {
            Level04 level04 = new Level04();
            while (true) {
                if (!level04.start(sc)) break;
            }
        }

        // Level03 계산기 동작
        if (calNum.equals(LEVEL_THREE.getCriteria())) {
            Level03 level03 = new Level03();
            while (true) {
                if (!level03.start(sc)) break;
            }
        }

        // Level02 계산기 동작
        if (calNum.equals(LEVEL_TWO.getCriteria())) {
            Level02 level02 = new Level02();
            level02.start(sc);
            level02.currentResults();   // 최근 연산 결과 5개 호출
        }

        // Level01 계산기 동작
        while (calNum.equals(LEVEL_ONE.getCriteria())) {

            // 연산에 필요한 값 입력 및 변수 저장 및 사용자의 계산기 수행 의사확인 과정
            // 입력 값 저장 후 바로 저장 값이 "exit"(종료 명령어)인지 확인하는 메서드 호출
            String firstInput = sc.nextLine();
            if (isExitCommand(firstInput)) break;
            String operator = sc.nextLine();
            if (isExitCommand(operator)) break;
            String secondInput = sc.nextLine();
            if (isExitCommand(secondInput)) break;

            // 입력 값 유효성 검사, 유효하지 않은 값이 있다면 반복문 처음으로
            if (!isValidCalculation(firstInput, operator, secondInput)) continue;

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

        sc.close();   // 입력 종료
        System.out.println(CALCULATOR_EXIT.getMessage());   // 계산기 종료 메시지
        System.exit(0);   // 계산기(프로그램) 정상 종료
    }

    /**
     * 아래는 Level01 계산기 구성에 필요한 메서드들
     * Level01 계산기는 'static main()` 에 구현했으므로 아래의 메서드들도 static 메서드로 구현
     */
    // 입력받은 값이 계산기 종료 명령어와 같은지 확인하는 메서드
    // 계산기 종료 명령어와 같다면 'true', 다르다면 'false' 반환
    public static boolean isExitCommand(String input) {
        final String exitCommand = "exit";   // 종료 명령어 지정
        return input.equals(exitCommand);
    }

    // 입력한 계산기 번호의 유효성을 검사하는 메서드
    // 통과시 true, 통과 실패시 false 반환
    public static boolean isValidCalculatorNumber(String num) {
        if (!Pattern.matches(CALCULATOR_NUMBER.getCriteria(), num)) {
            System.out.println(
                    ERROR_SELECT_CALCULATOR.getMessage() + "\n" +
                    RESULT_WINDOW_DIVIDER.getMessage()
            );
            return false;
        }
        return true;
    }

    // 연산결과를 출력하는 메서드
    public static void printResult(double result) {
        System.out.println("연산 결과 : " + result);
    }

    // 사칙연산 중 하나를 수행하는 메서드들
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
    public static boolean isValidCalculation(String firstNum, String operator, String secondNum) {
        final String NUMBER = "^(0|[1-9][0-9]*)$";   // 피연산자를 확인하는 정규식, level01 과 level02 계산기는 피연산자로 자연수와 '0' 만 입력 가능
        final String OPERATOR = "[+\\-*/]";   // 연산자를 확인하는 정규식

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
}