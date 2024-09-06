package calculator.level03.enums;

// 유효성 판단의 기준이 되는 값들을 열거한 enum 클래스
public enum ValidCriteria {

    CALCULATOR_NUMBER("[1-3]"), // 사용 가능한 계산기 번호
    LEVEL_ONE("1"),  // level01 계산기를 구분하는 값
    LEVEL_TWO("2"),  // level02 계산기를 구분하는 값
    LEVEL_THREE("3"), // level03
    OPERAND("^[-+]?(0|[1-9][0-9]*)(\\.[0-9]+)?$"),  // 피연산자(정수, 실수)
    OPERATOR("[+\\-*/]"),   // 연산자(사칙연산)
    EXIT_COMMAND("exit"),   // 계산기 종료 명령어
    ;

    private final String criteria;

    ValidCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }
}