package calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);    // 값 입력을 위한 Scanner 객체 생성

        // 계산기 입력 방식을 사용자에게 알리는 메시지
        System.out.println("연산 입력시 [피연산자 > 연산기호 > 피연산자] 순으로 입력해주세요!");

        while (true) {

            // 연산에 필요한 값 입력 및 변수 저장
            String firstInput = sc.nextLine();
            if (checkExit(firstInput)) {            // 첫 번째 입력 값을 통해 사용자가 계산을 계속할지 중단할지 판단
                System.out.println("계산기를 종료합니다.");
                break;
            }
            String operator = sc.nextLine();
            String secondInput = sc.nextLine();

            // 입력 값 유효성 검사, 유효하지 않은 값이 있다면 반복문 처음으로
            if (!validInputCheck(firstInput, operator, secondInput)) continue;

            // 나누기 연산을 염두해 입력받은 정수들을 실수형으로 변환, operator 는 그대로 사용
            double firstNum = Integer.parseInt(firstInput);
            double secondNum = Integer.parseInt(secondInput);

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

        System.exit(0); // main 메서드 실행 (정상)종료 지정
    }

    public static void printResult(double result) {
        System.out.println("연산 결과 : " + result);
    }

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
        final String NUMBER = "^[0-9]+$";
        final String OPERATOR = "[+\\-*/]";

        boolean result = true;
        if (!Pattern.matches(NUMBER, firstNum) || !Pattern.matches(NUMBER, secondNum)) {
            System.out.println("피연산자 입력오류 : 입력 가능한 피연사자는 자연수입니다.");
            result = false;
        }
        if (!Pattern.matches(OPERATOR, operator)) {
            System.out.println("연산자 입력오류 : 입력 가능한 연산자는 '+', '-', '*', '.' 입니다.");
            result = false;
        }
        if (!result) {
            System.out.println("처음부터 다시 입력 부탁드립니다.");
            System.out.println("===========================");
        }

        return result;
    }

    // 입력받은 문자열이 계산기를 종료하는 문자열(exitStr)인지 확인하는 메서드
    public static boolean checkExit(String input) {
        String exitStr = "exit";
        return input.equals(exitStr);
    }

    // 입력받은 문자열이 사용 가능한 기호인지 확인하는 메서드
    // 해당 메서드는 사망하였습니다.
//    public static boolean checkOperator(String input) {
//        final String OPERATOR = "[+\\-*/]";
//        if (!Pattern.matches(OPERATOR, input)) {
//            System.out.println("입력 가능한 값은 기호 '+', '-', '*', '/' 뿐입니다.");
//            System.out.println("처음부터 다시 입력 부탁드립니다.");
//            System.out.println("===========================");
//            return true;
//        }
//        return false;
//    }

    // 입력받은 문자열이 숫자로만 이루어졌는지 확인하는 메서드
    // 덩달아 같이 사망한 메서드
//    public static boolean checkNum(String input) {
//        final String NUMBER = "^[0-9]+$";   // 숫자가 반드시 있어야 하므로 해당 정규식을 사용했음
//        if (!Pattern.matches(NUMBER, input)) {
//            System.out.println("입력 가능한 값은 '0~9' 의 숫자들 뿐입니다.");
//            System.out.println("처음부터 다시 입력 부탁드립니다.");
//            System.out.println("===========================");
//            return true;
//        }
//        return false;
//    }
}