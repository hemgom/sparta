package numbersBaseball.domain.validateUserInput;

import numbersBaseball.exception.BadInputException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static numbersBaseball.enums.ExceptionMessage.BAD_INPUT_MENU_NUM;
import static numbersBaseball.enums.ExceptionMessage.BAD_INPUT_THREE_DIGITS;
import static numbersBaseball.enums.ValidCriteria.MENU_NUM;
import static numbersBaseball.enums.ValidCriteria.PLAYER_INPUT_THREE_DIGITS;

// InputValidator 인터페이스를 구현한 클래스
public class InputValidatorImpl implements InputValidator {
    /**
     * 입력된 메뉴 번호에 대한 검증 기능을 수행하는 메서드
     * 검증 위한 메서드를 호출하며 예외를 처리함
     */
    @Override
    public boolean isValidMenuNum(String menuNum) {
        try {
            checkValidMenuNum(menuNum);

        } catch (BadInputException e) {
            System.out.println(BAD_INPUT_MENU_NUM.getMessage());
        }

        return true;
    }

    private void checkValidMenuNum(String menuNum) throws BadInputException {
        if (!Pattern.matches(MENU_NUM.getCriteria(), menuNum))
            throw new BadInputException(BAD_INPUT_MENU_NUM.getMessage());
    }

    /**
     * 입력 값 유효성 검증 기능을 수행하는 메서드
     * 검증을 위한 두 개의 메서드를 호출해 예외를 처리
     */
    @Override
    public boolean isValidInput(String input) {
        try {
            checkInputPattern(input);
            checkInputElements(input);

        } catch (BadInputException bie) {
            System.out.println(bie.getMessage());
            return false;
        }

        return true;
    }

    /**
     * 아래의 두 메서드는 해당 클래스 내에서만 호출되므로 private 로 설정
     * checkInputPattern : 입력 값의 패턴이 유효한지 검증
     * checkInputElements : 입력 값의 각 요소에 중복이 없는지 검증
     */
    private void checkInputPattern(String input) throws BadInputException {
        if (!Pattern.matches(PLAYER_INPUT_THREE_DIGITS.getCriteria(), input))
            throw new BadInputException(BAD_INPUT_THREE_DIGITS.getMessage());
    }

    private void checkInputElements(String input) throws BadInputException {
        Set<Character> inputElements = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            if (!inputElements.add(input.charAt(i)))
                throw new BadInputException(BAD_INPUT_THREE_DIGITS.getMessage());
        }
    }
}