package numbersBaseball.enums;

import numbersBaseball.exception.BadInputException;

// 유효성 검증 기준을 필드로 갖는 상수를 열거한 enum 클래스
public enum ValidCriteria {
    PLAYER_INPUT_THREE_DIGITS("[1-9][1-9][1-9]", 3),
    PLAYER_INPUT_FOUR_DIGITS("[1-9][1-9][1-9][1-9]", 4),
    PLAYER_INPUT_FIVE_DIGITS("[1-9][1-9][1-9][1-9][1-9]", 5),
    SELECTABLE_LENGTH("[3-5]", 1),
    DEFAULT_LENGTH("3", 1),
    MENU_NUM("[0-3]", 1),
    MENU_NUM_ZERO("0", 1),
    MENU_NUM_ONE("1", 1),
    MENU_NUM_TWO("2", 1),
    MENU_NUM_THREE("3", 1),
    ;

    private final String criteria;
    private final int length;

    ValidCriteria(String criteria, int length) {
        this.criteria = criteria;
        this.length = length;
    }

    public String getCriteria() {
        return criteria;
    }

    public int getLength() {
        return length;
    }

    public static ValidCriteria findByLength(int length) throws BadInputException {
        for (ValidCriteria validCriteria : values()) {
            if (length == validCriteria.getLength()) return validCriteria;
        }
        throw new BadInputException("길이에 해당하는 정규식이 존재하지 않습니다.");
    }
}
