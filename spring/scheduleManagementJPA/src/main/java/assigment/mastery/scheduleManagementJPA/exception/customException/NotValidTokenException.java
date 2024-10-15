package assigment.mastery.scheduleManagementJPA.exception.customException;

import assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class NotValidTokenException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public NotValidTokenException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
