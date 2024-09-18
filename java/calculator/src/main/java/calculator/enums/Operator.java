package calculator.enums;

import calculator.level03.calculation.*;

// 사용 연산자 값을 설정하는 Enum 클래스
public enum Operator {
    ADD("+", new AddOperation<>()),   // 더하기 연산자
    SUB("-", new SubOperation<>()),   // 빼기 연산자
    MUL("*", new MulOperation<>()),   // 곱하기 연산자
    DIV("/", new DivOperation<>()),   // 나누기 연산자
    ;

    private final String operator;
    private final Operation<Double> operation;

    Operator(String operator, Operation<Double> operation) {
        this.operator = operator;
        this.operation = operation;
    }

    public String getOperator() {
        return operator;
    }

    private Operation<Double> getOperation() {
        return operation;
    }

    public static Operation<Double> findByOperator(String operator) {
        for (Operator o : values()) {
            if (operator.equals(o.getOperator())) return o.getOperation();
        }
        throw new IllegalArgumentException("입력한 연산자를 조회할 수 없습니다.");
    }
}