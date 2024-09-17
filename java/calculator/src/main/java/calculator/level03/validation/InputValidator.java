package calculator.level03.validation;

import java.util.regex.Pattern;

import static calculator.enums.SystemMessage.*;
import static calculator.enums.ValidCriteria.*;

// 입력 값의 유효성을 확인하는 클래스, Enum 클래스의 열거 상수를 사용함
public class InputValidator {

    /**
     * 계산기 종료 명령어(입력값)에 대한 유효성을 확인하는 메서드
     * 설정한 명령어와 같은 문자열이 입력되었다면 true, 아니라면 false 반환
     */
    public boolean isExitCommand(String input) {
        return input.equals(EXIT_COMMAND.getCriteria());
    }

    /**
     * 입력한 값(연산자 & 피연산자)의 유효성을 확인하는 메서드
     * 연산자와 피연산자 각각 유효성을 확인
     * validCalculation() 에서 최종적으로 전체 유효성 확인 후 시스템 메시지 출력
     * 유효하지 않은 값이 없다면 true, 있다면 false 반환
     */
    public boolean isValidCalculation(String firstOperand, String operator, String secondOperand) {
        boolean result = true;
        if (!isValidOperand(firstOperand) || !isValidOperand(secondOperand)) {
            System.out.println(ERROR_INPUT_OPERAND.getMessage());
            result = false;
        }
        if (!isValidOperator(operator)) {
            System.out.println(ERROR_INPUT_OPERATOR.getMessage());
            result = false;
        }
        if (!result) System.out.println(
                INPUT_RETRY_PLEASE.getMessage() + "\n"
                + RESULT_WINDOW_DIVIDER.getMessage()
        );
        return result;
    }

    // 입력받은 피연산자 값이 유효하다면 true, 아니라면 false 반환
    private boolean isValidOperand(String operand) {
        return Pattern.matches(OPERAND.getCriteria(), operand);
    }

    // 입력받은 연산자 값이 유효하다면 true, 아니라면 false 반환
    private boolean isValidOperator(String operator) {
        return Pattern.matches(OPERATOR.getCriteria(), operator);
    }

}
