package calculator.enums;

// 사용 연산자 값을 설정하는 Enum 클래스
public enum Operator {
    ADD("+"),   // 더하기 연산자
    SUB("-"),   // 빼기 연산자
    MUL("*"),   // 곱하기 연산자
    DIV("/"),   // 나누기 연산자
    ;

    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}