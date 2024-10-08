package calculator.level03.calculation;

// Operation<T extends Number> 인터페이스를 구현한 클래스, 곱셈 연산 메서드를 구현
public class MulOperation<T extends Number> implements Operation<T> {
    @Override
    public double calculate(T firstNum, T secondNum) {
        return firstNum.doubleValue() * secondNum.doubleValue();
    }
}
