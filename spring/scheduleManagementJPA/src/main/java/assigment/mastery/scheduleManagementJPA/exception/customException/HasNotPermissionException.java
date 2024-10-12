package assigment.mastery.scheduleManagementJPA.exception.customException;

import assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class HasNotPermissionException extends RuntimeException {
    public final ExceptionCode exceptionCode;

    public HasNotPermissionException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
