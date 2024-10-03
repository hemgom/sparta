package assignment.introductory.scheduleManagement.exception.customException;

import assignment.introductory.scheduleManagement.exception.exceptionCode.ExceptionCode;
import lombok.Getter;

@Getter
public class NotFoundSearchInfoException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public NotFoundSearchInfoException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
