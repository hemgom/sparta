package calculator.level02.calculate;

// 사칙연산을 수행하는 추상메서드를 가진 인터페이스
public interface Operation {
    double calculate(double firstNum, double secondNum);
}