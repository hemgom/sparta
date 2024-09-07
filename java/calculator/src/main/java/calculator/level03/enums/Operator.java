package calculator.level03.enums;

// 사용 연산자 값을 설정하는 Enum 클래스
public enum Operator {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    ;

    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}