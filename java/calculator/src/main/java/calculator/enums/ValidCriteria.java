package calculator.enums;

// 유효성 판단의 기준이 되는 값들을 열거한 enum 클래스
public enum ValidCriteria {
    CALCULATOR_NUMBER("[1-4]"), // 사용 가능한 계산기 값
    LEVEL_ONE("1"),  // level01 계산기를 구분하는 값
    LEVEL_TWO("2"),  // level02 계산기를 구분하는 값
    LEVEL_THREE("3"), // level03 계산기를 구분하는 값
    LEVEL_FOUR("4"), // level04 계산기를 구분하는 값
    OPERAND("^[-+]?(0|[1-9][0-9]*)(\\.[0-9]+)?$"),  // 피연산자(정수 or 실수)
    OPERATOR("[+\\-*/]"),   // 연산자(연산기호)
    CALCULATION("^([-+]?(0|[1-9][0-9]*)(\\.[0-9]+)?)[' '][+\\-*/][' ']([-+]?(0|[1-9][0-9]*)(\\.[0-9]+)?)$"),   // 하나의 사칙연산
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