package numbersBaseball.enums;

import numbersBaseball.exception.BadInputException;

/**
 * 유효성 검증 기준 값을 필드로 갖는 상수들을 보유한 enum 클래스
 */
public enum ValidCriteria {
    SELECTABLE_LENGTH("[3-5]"),
    DEFAULT_LENGTH("3"),
    MENU_NUM("[0-3]"),
    MENU_NUM_ZERO("0"),
    MENU_NUM_ONE("1"),
    MENU_NUM_TWO("2"),
    MENU_NUM_THREE("3"),
    ;

    private final String criteria;

    ValidCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }
}
