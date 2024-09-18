package calculator.level02.calculation;

import java.util.LinkedList;
import java.util.Queue;

// 입력 받은 값으로 연산을 수행하는 클래스
public class Calculator {
    private double firstNum;
    private double secondNum;
    private final Queue<Double> calculateResults = new LinkedList<>();

    public void setFirstNum(String num) {
        firstNum = Double.parseDouble(num);
    }
    
    public void setSecondNum(String num) {
        secondNum = Double.parseDouble(num);
    }

    // 연산 객체의 연산 메서드(calculate)를 호출하는 메서드
    public double calculate(Operation operation) {
        return operation.calculate(firstNum, secondNum);
    }

    // 최근 연산한 결과들을 저장하는 메서드, 최대 5개까지 최근 데이터를 저장
    // 만약 저장된 데이터가 5개가 넘었다면 가장 오래된 결과 값을 삭제하고 새로운 값으 추가함
    public void saveResult(double result) {
        if (calculateResults.size() > 4) calculateResults.poll();
        calculateResults.offer(result);
    }

    // 최근 연산한 결과들을 저장한 Collection 객체를 반환하는 메서드
    public Queue<Double> getCalculateResults() {
        return calculateResults;
    }
}
