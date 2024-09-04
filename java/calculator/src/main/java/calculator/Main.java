package calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);    // 값 입력을 위한 Scanner 객체 생성

        // 계산기 입력 방식을 사용자에게 알리는 메시지
        System.out.println("연산 입력시 [피연산자 > 연산기호 > 피연산자] 순으로 입력해주세요!");

        while (true) {

            String firstInput = sc.nextLine();
            if (checkExit(firstInput)) {            // 첫 번째 입력 값을 통해 사용자가 계산을 계속할지 중단할지 판단
                System.out.println("계산기를 종료합니다.");
                break;
            }
            if (checkNum(firstInput)) continue;    // 지정한 패턴에 맞지 않으므로 반복문 처음으로 돌아가 수행

            String operator = sc.nextLine();
            if (checkOperator(operator)) continue;   // 사용 가능한 연산기호가 아니라면 반복문 처음으로 돌아가 수행

            String secondInput = sc.nextLine();
            if (checkNum(secondInput)) continue;

            // 나누기 연산을 염두해 입력받은 정수들을 실수형으로 변환, operator 는 그대로 사용
            double firstNum = Integer.parseInt(firstInput);
            double secondNum = Integer.parseInt(secondInput);

            // 사칙연산 수행
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "*":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    result = firstNum / secondNum;
                    break;
            };

            System.out.println("입력 연산 : " + firstNum + operator + secondNum);
            System.out.println("연산 결과 : " + result);
            System.out.println("===========================");

        }

        System.exit(0); // main 메서드 실행 (정상)종료 지정
    }

    // 입력받은 문자열이 사용 가능한 기호인지 확인하는 메서드
    public static boolean checkOperator(String input) {
        final String OPERATOR = "[+\\-*/]";
        if (!Pattern.matches(OPERATOR, input)) {
            System.out.println("입력 가능한 값은 기호 '+', '-', '*', '/' 뿐입니다.");
            System.out.println("처음부터 다시 입력 부탁드립니다.");
            System.out.println("===========================");
            return true;
        }
        return false;
    }

    // 입력받은 문자열이 숫자로만 이루어졌는지 확인하는 메서드
    public static boolean checkNum(String input) {
        final String NUMBER = "^[0-9]+$";   // 숫자가 반드시 있어야 하므로 해당 정규식을 사용했음
        if (!Pattern.matches(NUMBER, input)) {
            System.out.println("입력 가능한 값은 '0~9' 의 숫자들 뿐입니다.");
            System.out.println("처음부터 다시 입력 부탁드립니다.");
            System.out.println("===========================");
            return true;
        }
        return false;
    }

    // 입력받은 문자열이 계산기를 종료하는 문자열(exitStr)인지 확인하는 메서드
    public static boolean checkExit(String input) {
        String exitStr = "exit";
        return input.equals(exitStr);
    }
}