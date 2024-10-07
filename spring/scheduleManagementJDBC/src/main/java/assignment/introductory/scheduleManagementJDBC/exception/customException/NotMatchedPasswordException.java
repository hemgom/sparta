package assignment.introductory.scheduleManagementJDBC.exception.customException;

import assignment.introductory.scheduleManagementJDBC.exception.exceptionCode.ExceptionCode;
import lombok.Getter;

@Getter
public class NotMatchedPasswordException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public NotMatchedPasswordException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
