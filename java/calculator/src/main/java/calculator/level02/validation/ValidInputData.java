package calculator.level02.validation;

import java.util.regex.Pattern;

// 입력 받은 값들의 유효성을 검사하는 클래스
public class ValidInputData {

    private static final String NUMBER = "^[0-9.]+$";
    private static final String OPERATOR = "[+\\-*/]";
    private static final String EXIT = "exit";

    /**
     * 입력한 피연산자의 유효성을 검사하는 메서드
     * 유효성 통과시 false, 통과 실패시 true 반환
     * 이미 Main 클래스에 같은 수행을 하는 static 메서드로 등록되어있어 경고문구가 떠 반환 결과를 반대로하였다.
     * 해당 static 메서드를 가져다 써도 상관없지만 서로 다른 방법으로 구현한 계산기를 구분하고 싶어 이런 방법을 택했다.
     * 아마 하나의 계산기를 만들었다면 사용하지 않을 방법이다.
     */
    public boolean validInputData(String firstNum, String operator, String secondNum) {
        boolean result = false;
        if (!Pattern.matches(NUMBER, firstNum) || !Pattern.matches(NUMBER, secondNum)) {
            System.out.println("피연산자 입력오류 : 입력 가능한 피연사자는 자연수입니다.");
            result = true;
        }
        if (!Pattern.matches(OPERATOR, operator)) {
            System.out.println("연산자 입력오류 : 입력 가능한 연산자는 '+', '-', '*', '.' 입니다.");
            result = true;
        }
        if (result) {
            retryMessage();
        }

        return result;
    }

    // 입력한 값이 "exit" 인지 확인하는 메서드
    // 유효성 통과시 true, 통과 실패시 false 반환
    public boolean validExit(String input) {
        return Pattern.matches(EXIT, input);
    }

    public void retryMessage() {
        System.out.println(
                "처음부터 다시 입력 부탁드립니다.\n" +
                        "==========================="
        );
    }
}