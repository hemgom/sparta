package calculator.level02.validation;

import calculator.Main;

import java.util.regex.Pattern;

// 입력 받은 값들의 유효성을 검사하는 클래스
public class ValidInput {
    private static final String EXIT = "exit";

    /**
     * 입력한 피연산자의 유효성을 검사하는 메서드
     * 유효성 통과시 true, 통과 실패시 false 반환
     * 같은 기능을 하는 메서드가 Main 클래스에 static 메서드로 구현되어있기에 가져와 사용
     * 불필요한 중복 코드 방지를 위해 이와 같은 방법을 사용
     */
    public boolean validInput(String firstNum, String operator, String secondNum) {
        return Main.checkInput(firstNum, operator, secondNum);
    }

    // 입력한 값이 "exit" 인지 확인하는 메서드
    // 유효성 통과시 true, 통과 실패시 false 반환
    public boolean validExit(String input) {
        return Pattern.matches(EXIT, input);
    }

}