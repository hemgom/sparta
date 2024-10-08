package assigment.mastery.scheduleManagementJPA.exception.customException;

import assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class NotFoundEntityException extends RuntimeException{
    private final ExceptionCode exceptionCode;

    public NotFoundEntityException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
