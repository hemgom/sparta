package calculator.level02.calculation;

import java.util.HashMap;
import java.util.Map;

// Operation 인터페이스를 구현한 클래스 객체를 매핑하는 역할을 수행
public class OperationMapper {
    // 입력받은 연산기호를 key, 연산 객체를 value 로 사용하는 Map 객체 생성
    private final Map<String, Operation> operation = new HashMap<>();

    // 연산자를 파라미터로 받아 해당하는 연산객체를 생성하는 메서드
    // 이미 연산객체가 생성된 경우 새로운 연산객체를 생성하지 않음
    public void addOperation(String operator) {
        switch (operator) {
            case "+":
                operation.computeIfAbsent(operator, k -> new AddOperation());
                break;
            case "-":
                operation.computeIfAbsent(operator, k -> new SubOperation());
                break;
            case "*":
                operation.computeIfAbsent(operator, k -> new MulOperation());
                break;
            case "/":
                operation.computeIfAbsent(operator, k -> new DivOperation());
                break;
        }
    }

    // 파라미터로 전달받은 연산자의 연산객체를 반환하는 메서드
    public Operation getOperation(String operator) {
        return operation.get(operator);
    }
}
