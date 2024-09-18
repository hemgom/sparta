package calculator.level04.exception;

import static calculator.enums.SystemMessage.*;

public class DivideByZeroException extends Exception {

    public DivideByZeroException() {
        super(CANNOT_DIVIDE_BY_ZERO.getMessage() + "\n" +
                RESULT_WINDOW_DIVIDER.getMessage());
    }

}
