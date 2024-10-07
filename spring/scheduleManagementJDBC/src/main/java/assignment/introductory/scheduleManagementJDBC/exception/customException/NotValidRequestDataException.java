package assignment.introductory.scheduleManagementJDBC.exception.customException;

import assignment.introductory.scheduleManagementJDBC.exception.exceptionCode.ExceptionCode;
import lombok.Getter;

@Getter
public class NotValidRequestDataException extends RuntimeException {
    private final ExceptionCode exceptionCode;
    private final String fieldName;

    public NotValidRequestDataException(ExceptionCode exceptionCode, String fieldName) {
        this.exceptionCode = exceptionCode;
        this.fieldName = fieldName;
    }
}
