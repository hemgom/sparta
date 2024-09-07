package calculator.level03.calculate;

public class AddOperation<T extends Number> implements Operation<T> {
    @Override
    public double calculate(T firstNum, T secondNum) {
        return firstNum.doubleValue() + secondNum.doubleValue();
    }
}
