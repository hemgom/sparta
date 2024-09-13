package numbersBaseball.domain.playerInput;

import numbersBaseball.exception.BadInputException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static numbersBaseball.enums.ValidCriteria.PLAYER_INPUT_THREE_DIGITS;

// 플레이어의 입력(세 자리 숫자)의 유효성을 검증하는 역할
public class ThreeDigitValidator implements InputValidator {
    private final int inputLength = 3;

    /**
     * 입력 값 유효성 검증 기능을 수행하는 메서드
     * 검증을 위한 두 개의 메서드를 호출해 예외를 처리
     */
    @Override
    public boolean isValid(String input) {
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
        if (!Pattern.matches(PLAYER_INPUT_THREE_DIGITS.getCriteria(), input)) {
            throw new BadInputException(inputLength);
        }
    }

    private void checkInputElements(String input) throws BadInputException {
        Set<Character> inputElements = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            if (!inputElements.add(input.charAt(i))) throw new BadInputException(inputLength);
        }
    }
}