package calculator.level03.calculation;

import java.util.HashMap;
import java.util.Map;

import static calculator.enums.Operator.*;

// 연산자에 해당하는 연산객체와 매핑하는 역할의 클래스
public class OperationMapper<T extends Number> {
    // 입력받은 연산기호를 key, 연산 객체를 value 로 사용하는 Map 객체 생성
    private final Map<String, Operation<T>> operation = new HashMap<>();

    // 연산자를 파라미터로 받아 해당하는 연산객체를 생성하는 메서드
    // 이미 연산객체가 생성된 경우 새로운 연산객체를 생성하지 않음
    public void addOperation(String operator) {
        if (operator.equals(ADD.getOperator()))
            operation.computeIfAbsent(operator, k -> new AddOperation<>());

        if (operator.equals(SUB.getOperator()))
            operation.computeIfAbsent(operator, k -> new SubOperation<>());

        if (operator.equals(MUL.getOperator()))
            operation.computeIfAbsent(operator, k -> new MulOperation<>());

        if (operator.equals(DIV.getOperator()))
            operation.computeIfAbsent(operator, k -> new DivOperation<>());
    }

    // 파라미터로 전달받은 연산자의 연산객체를 반환하는 메서드
    public Operation<T> getOperation(String operator) {
        return operation.get(operator);
    }
}
