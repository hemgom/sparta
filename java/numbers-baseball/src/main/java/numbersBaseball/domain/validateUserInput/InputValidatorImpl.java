package numbersBaseball.domain.validateUserInput;

import numbersBaseball.enums.ValidCriteria;
import numbersBaseball.exception.BadInputException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static numbersBaseball.enums.ExceptionMessage.*;
import static numbersBaseball.enums.ValidCriteria.*;

// InputValidator 인터페이스를 구현한 클래스
public class InputValidatorImpl implements InputValidator {
    /**
     * 입력된 난이도(정답의 길이)에 대한 검증 기능을 수행하는 메서드
     * 검증을 위한 메서드를 호출하며 예외를 처리함
     */
    @Override
    public boolean isValidDifficultyLevel(String difficultyLevel) {
        try {
            checkValidDifficultyLevel(difficultyLevel);

        } catch (BadInputException bie) {
            System.out.println(bie.getMessage());
            return false;
        }

        return true;
    }

    private void checkValidDifficultyLevel(String difficultyLevel) throws BadInputException {
        if (!Pattern.matches(SELECTABLE_LENGTH.getCriteria(), difficultyLevel))
            throw new BadInputException(BAD_INPUT_LENGTH.getMessage());
    }

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
            return false;
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
    public boolean isValidInput(String input, int correctAnswerLength) {
        try {
            // 현재 생성된 정답에 해당하는 정규식을 조회, 입력 값이 해당 정규식을 만족하는지 확인
            checkInputPattern(input, findByLength(correctAnswerLength));
            // 입력 값 요소의 중복을 확인
            checkInputElements(input);

        } catch (BadInputException bie) {
            System.out.println(bie.getMessage());
            return false;
        }

        return true;
    }

    /**
     * 아래의 두 메서드는 해당 클래스 내에서만 호출되므로 private 로 설정
     * checkInputPattern : 입력 값의 패턴이 유효한지(정규식을 만족하는지) 확인
     * checkInputElements : 입력 값의 각 요소에 중복이 없는지 확인
     */
    private void checkInputPattern(String input, ValidCriteria regex) throws BadInputException {
        if (!Pattern.matches(regex.getCriteria(), input))
            throw new BadInputException(regex.getLength() + BAD_INPUT_NOT_MATCH_REGEX.getMessage());
    }

    private void checkInputElements(String input) throws BadInputException {
        Set<Character> inputElements = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            if (!inputElements.add(input.charAt(i)))
                throw new BadInputException(BAD_INPUT_SAME_NUM.getMessage());
        }
    }
}