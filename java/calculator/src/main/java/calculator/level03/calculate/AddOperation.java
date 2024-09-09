package calculator.level03.calculate;

// Operation<T extends Number> 인터페이스를 구현한 클래스, 덧셈 연산 메서드를 구현
public class AddOperation<T extends Number> implements Operation<T> {
    @Override
    public double calculate(T firstNum, T secondNum) {
        return firstNum.doubleValue() + secondNum.doubleValue();
    }
}
