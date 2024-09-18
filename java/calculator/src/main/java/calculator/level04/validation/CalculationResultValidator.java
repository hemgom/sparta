package calculator.level04.validation;

import calculator.level04.exception.DivideByZeroException;

// 연산 결과의 유효성을 검증하는 역할의 클래스
public class CalculationResultValidator {
    public void checkDivideByZero(double result) throws DivideByZeroException {
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            throw new DivideByZeroException();
        }
    }
}
