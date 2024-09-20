package numbersBaseball.enums;

// 유효성 검증 기준을 필드로 갖는 상수를 열거한 enum 클래스
public enum ValidCriteria {
    PLAYER_INPUT_THREE_DIGITS("[1-9][1-9][1-9]"),
    MENU_NUM("[1|3]"),
    FIRST_MENU_NUM("1"),
    SECOND_MENU_NUM("2"),
    THIRD_MENU_NUM("3"),
    ;

    private final String criteria;

    ValidCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }
}
