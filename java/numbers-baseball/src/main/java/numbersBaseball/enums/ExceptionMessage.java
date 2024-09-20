package numbersBaseball.enums;

// 예외 메시지를 필드로 갖는 상수를 열거한 enum 클래스
public enum ExceptionMessage {
    BAD_INPUT_THREE_DIGITS(
            "입력 값은 3자리의 서로 다른 숫자로 구성되어야 합니다.\n" +
            "=============================="
    ),
    BAD_INPUT_MENU_NUM(
            "올바른 메뉴 번호를 입력해주세요.\n" +
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
