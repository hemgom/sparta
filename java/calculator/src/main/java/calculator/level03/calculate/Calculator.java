package calculator.level03.calculate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static calculator.level03.enums.Operator.*;

// 요구사항은 정수(Integer) 입력에서 실수(Double) 입력으로 바뀌어, 타입을 선택할 수 있는 Generic 클래스 생성
public class Calculator<T extends Number> {
    private T firstNum;
    private T secondNum;
    private Operation<T> operation;
    private final Queue<Double> calculateResults = new LinkedList<>();

    /**
     * Generics 를 쓰게 되면서 클래스 외부에서 타입(T)을 정하게 되었다.
     * 그래서 피연산자에 값을 지정하는 메서드들도 파라미터로 String 타입이 아닌 지정 타입(T)을 파라미터로 받게 되었다.
     */
    public void setFirstNum(T num) {
        this.firstNum = num;
    }
    public void setSecondNum(T num) {
        this.secondNum = num;
    }

    /**
     * Enum 클래스의 열거 상수를 활용한 메서드
     * 지정한 연산 기호와 입력 값을 비교해 해당하는 연산 클래스 객체를 생성
     * Operation 타입의 변수에 객체를 저장해 연산에 해당하는 연산 클래스 객체를 사용가능
     */
    public void setOperation(String operator) {
        if (operator.equals(ADD.getOperator()))
            operation = new AddOperation<>();

        if (operator.equals(SUB.getOperator()))
            operation = new SubOperation<>();

        if (operator.equals(MUL.getOperator()))
            operation = new MulOperation<>();

        if (operator.equals(DIV.getOperator()))
            operation = new DivOperation<>();
    }

    public double calculate() {
        return operation.calculate(firstNum, secondNum);
    }

    // 연산 결과를 Collection 에 저장하는 메서드
    // 최근 연산 결과는 5개까지 저장되도록 구현
    public void saveResult(double result) {
        if (calculateResults.size() > 4) calculateResults.poll();
        calculateResults.offer(result);
    }

    // 최근 연산 결과를 저장해둔 Collection 을 반환한 메서드
    public Queue<Double> getCalculateResults() {
        return calculateResults;
    }

    // 연산 결과를 저장해둔 Collection 객체에서 현재 연산 결과보다 큰 요소들을 List 로 반환하는 메서드
    public List<Double> getBiggerResults(double result) {
        return calculateResults.stream()
                .filter((a) -> result < a)
                .toList();
    }
}