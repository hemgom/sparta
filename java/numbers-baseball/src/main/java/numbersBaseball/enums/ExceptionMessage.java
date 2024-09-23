package numbersBaseball.enums;

/**
 * '예외 메시지' 를 필드로 갖는 상수들을 보유한 enum 클래스
 */
public enum ExceptionMessage {
    INPUT_NOT_MATCH_REGEX(
            "자리의 숫자(1~9)로 구성된 값을 입력해 주세요.\n" +
            "=============================="
    ),
    DIGITS_NOT_UNIQUE(
            "서로 다른 숫자로 구성된 값을 입력해 주세요.\n" +
            "=============================="
    ),
    MENU_NOT_MATCH_REGEX(
            "올바른 메뉴를 선택해 주세요.\n" +
            "=============================="
    ),
    NUMBER_LENGTH_NOT_MATCH_REGEX(
            "자리수는 [3, 4, 5] 중에서 선택해 주세요.\n" +
            "=============================="
    ),
    NO_REGEX_FOR_LENGTH("길이에 해당하는 정규식이 존재하지 않습니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
