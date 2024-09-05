package calculator.level02.calculate;

// 인터페이스 Operation 구현한 클래스, 덧셈 연산을 하는 메서드를 가짐
public class AddOperation implements Operation {
    @Override
    public double calculate(double firstNum, double secondNum) {
        return firstNum + secondNum;
    }
}
