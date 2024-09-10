package calculator.level04.exception;

import static calculator.enums.SystemMessage.ERROR_INPUT_CALCULATION;
import static calculator.enums.SystemMessage.RESULT_WINDOW_DIVIDER;

public class BadInputException extends Exception {

    public BadInputException() {
        super(ERROR_INPUT_CALCULATION.getMessage() + "\n" +
                RESULT_WINDOW_DIVIDER.getMessage());
    }

}
