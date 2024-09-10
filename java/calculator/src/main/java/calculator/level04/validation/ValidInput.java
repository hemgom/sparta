package calculator.level04.validation;

import calculator.level04.exception.BadInputException;

import java.util.regex.Pattern;

import static calculator.enums.ValidCriteria.CALCULATION;
import static calculator.enums.ValidCriteria.EXIT_COMMAND;

// 입력 값의 유효성을 확인하는 클래스, Exception(예외)을 활용
public class ValidInput {

    // 입력 값이 종료 명령어인지 확인하는 메서드, 종료 명령어라면 true 를 아니라면 false 반환
    public boolean validExit(String input) {
        return input.equals(EXIT_COMMAND.getCriteria());
    }

    // 입력 값의 유효성을 검증하는 메서드, 유효하지 않을 경우 BadInputException 발생
    public void validCalculation(String input) throws BadInputException {
        if (!Pattern.matches(CALCULATION.getCriteria(), input)) {
            throw new BadInputException();
        }
    }

}
