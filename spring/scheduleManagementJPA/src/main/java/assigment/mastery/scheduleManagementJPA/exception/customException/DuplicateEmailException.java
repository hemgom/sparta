package assigment.mastery.scheduleManagementJPA.exception.customException;

import assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class DuplicateEmailException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public DuplicateEmailException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
