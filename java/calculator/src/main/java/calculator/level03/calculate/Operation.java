package calculator.level03.calculate;

public interface Operation <T extends Number> {
    double calculate(T firstNum, T secondNum);
}
