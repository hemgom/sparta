package numbersBaseball.enums;

// 유효성 검증 기준을 값으로 갖는 Enum 클래스
public enum ValidCriteria {
    PLAYER_INPUT_THREE_DIGITS("[1-9][1-9][1-9]"),
    ;

    private final String criteria;

    ValidCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }
}
