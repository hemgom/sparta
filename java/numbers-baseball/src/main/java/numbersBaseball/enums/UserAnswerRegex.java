package numbersBaseball.enums;

import numbersBaseball.exception.BadInputException;
import numbersBaseball.exception.NotExistConstantsException;

import static numbersBaseball.enums.ExceptionMessage.NO_REGEX_FOR_LENGTH;

/**
 * 사용자 응답의 유효성을 검증할 때 사용하는 '정규식(Regex)' 과 정규식의 길이를 필드로 갖는 상수들을 보유한 enum 클래스
 */
public enum UserAnswerRegex {
    THREE_DIGITS_USER_ANSWER("[1-9][1-9][1-9]", 3),
    FOUR_DIGITS_USER_ANSWER("[1-9][1-9][1-9][1-9]", 4),
    FIVE_DIGITS_USER_ANSWER("[1-9][1-9][1-9][1-9][1-9]", 5),
    ;

    private final String regex;
    private final int length;

    UserAnswerRegex(String regex, int length) {
        this.regex = regex;
        this.length = length;
    }

    public String getRegex() {
        return regex;
    }

    public int getLength() {
        return length;
    }

    /**
     * 해당 길이(length)를 필드로 갖는 상수를 조회하는 기능 수행, 조건에 맞는 상수가 없다면 'NotExistConstantsException' 발생
     */
    public static UserAnswerRegex findByLength(int length) throws NotExistConstantsException {
        for (UserAnswerRegex userAnswerRegex : values()) {
            if (length == userAnswerRegex.getLength()) return userAnswerRegex;
        }
        throw new NotExistConstantsException(NO_REGEX_FOR_LENGTH.getMessage());
    }
}
