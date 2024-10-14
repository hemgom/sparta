package assigment.mastery.scheduleManagementJPA.exception.customException;

import assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class NotMatchPasswordException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public NotMatchPasswordException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
