package calculator.level03.calculation;

// Calculator<T extends Number> 클래스에 사용하기 위해 해당 인터페이스도 Generic 을 사용해 생성함
public interface Operation <T extends Number> {
    double calculate(T firstNum, T secondNum);
}
