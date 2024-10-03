package assignment.introductory.scheduleManagement.exception.customException;

import assignment.introductory.scheduleManagement.exception.exceptionCode.ExceptionCode;
import lombok.Getter;

@Getter
public class NotMatchedPasswordException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public NotMatchedPasswordException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
