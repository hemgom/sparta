package numbersBaseball.enums;

// 예외 메시지를 필드로 갖는 상수를 열거한 enum 클래스
public enum ExceptionMessage {
    BAD_INPUT_NOT_MATCH_REGEX(
            "자리의 숫자(1~9)로 입력 값은 구성되어야 합니다.\n" +
            "=============================="
    ),
    BAD_INPUT_SAME_NUM(
            "입력 값의 각 자리 숫자는 서로 달라야 합니다.\n" +
            "=============================="
    ),
    BAD_INPUT_MENU_NUM(
            "올바른 메뉴 번호를 입력해주세요.\n" +
            "=============================="
    ),
    BAD_INPUT_LENGTH(
            "선택 가능한 자리수는 3, 4, 5 입니다.\n" +
            "=============================="
    ),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
