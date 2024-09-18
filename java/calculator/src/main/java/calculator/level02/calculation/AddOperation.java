package calculator.level02.calculation;

// Operation 인터페이스를 구현한 클래스, 덧셈 연산을 수행하는 메서드를 구현
public class AddOperation implements Operation {
    @Override
    public double calculate(double firstNum, double secondNum) {
        return firstNum + secondNum;
    }
}
