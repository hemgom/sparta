package numbersBaseball.domain.validateUserInput;

import numbersBaseball.enums.UserAnswerRegex;
import numbersBaseball.exception.BadInputException;
import numbersBaseball.exception.NotExistConstantsException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static numbersBaseball.enums.ExceptionMessage.*;
import static numbersBaseball.enums.ValidCriteria.MENU_NUM;
import static numbersBaseball.enums.ValidCriteria.SELECTABLE_LENGTH;
import static numbersBaseball.enums.UserAnswerRegex.findByLength;

// InputValidator 인터페이스를 구현한 클래스
public class InputValidatorImpl implements InputValidator {
    @Override
    public boolean isValidNumberLength(String numberLength) {
        try {
            // 사용자가 선택한 '정답 길이' 가 정규식을 만족하는지 확인
            checkNumberLength(numberLength);

        } catch (BadInputException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean isValidMenuNumber(String menuNum) {
        try {
            // 사용자가 선택한 '메뉴 번호' 가 정규식을 만족하는지 확인
            checkMenuNum(menuNum);

        } catch (BadInputException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean isValidInput(String input, int correctAnswerLength) {
        try {
            // 정답 길이에 해당하는 정규식을 조회해 입력 값이 해당 정규식을 만족하는지 확인
            checkInputPattern(input, findByLength(correctAnswerLength));

            // 입력 값 요소의 중복을 확인
            checkInputElements(input);

        } catch (BadInputException | NotExistConstantsException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * '정답 길이(자리수)' 가 지정한 '정규식(Regex)' 을 만족하는지 확인, 만족하지 못 한다면 'BadInputException' 예외 발생
     */
    private void checkNumberLength(String numberLength) throws BadInputException {
        if (!Pattern.matches(SELECTABLE_LENGTH.getCriteria(), numberLength))
            throw new BadInputException(NUMBER_LENGTH_NOT_MATCH_REGEX.getMessage());
    }

    /**
     * '메뉴 번호' 가 지정한 '정규식(Regex)' 을 만족하는지 확인, 만족하지 못 한다면 'BadInputException' 예외 발생
     */
    private void checkMenuNum(String menuNum) throws BadInputException {
        if (!Pattern.matches(MENU_NUM.getCriteria(), menuNum))
            throw new BadInputException(MENU_NOT_MATCH_REGEX.getMessage());
    }

    /**
     * 정답을 맞추기 위해 입력한 값이 지정한 '정규식(Regex)' 을 만족하는지 확인, 만족하지 못 한다면 'BadInputException' 예외 발생
     */
    private void checkInputPattern(String input, UserAnswerRegex regex) throws BadInputException {
        if (!Pattern.matches(regex.getRegex(), input))
            throw new BadInputException(regex.getLength() + INPUT_NOT_MATCH_REGEX.getMessage());
    }

    /**
     * 정답을 맞추기 위해 입력한 값에 '중복 요소' 가 있는지 확인, 있다면 'BadInputException' 예외 발생
     */
    private void checkInputElements(String input) throws BadInputException {
        Set<Character> inputElements = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            if (!inputElements.add(input.charAt(i)))
                throw new BadInputException(DIGITS_NOT_UNIQUE.getMessage());
        }
    }
}