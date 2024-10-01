package assignment.introductory.scheduleManagement.exception.customException;

import assignment.introductory.scheduleManagement.exception.exceptionCode.ExceptionCode;
import lombok.Getter;

@Getter
public class NotMatchedConstantException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public NotMatchedConstantException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
