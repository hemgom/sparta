package calculator.level03.validation;

import static calculator.enums.SystemMessage.CANNOT_DIVIDE_BY_ZERO;
import static calculator.enums.SystemMessage.RESULT_WINDOW_DIVIDER;

// 연산 결과의 유효성을 검증하는 클래스
public class CalculationResultValidator {
    public boolean isValidCalculationResult(double result) {
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            System.out.println(
                    CANNOT_DIVIDE_BY_ZERO.getMessage() + "\n"
                    + RESULT_WINDOW_DIVIDER.getMessage()
            );
            return false;
        }

        return true;
    }
}
