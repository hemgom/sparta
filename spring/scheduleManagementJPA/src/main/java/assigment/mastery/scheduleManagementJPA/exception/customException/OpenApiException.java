package assigment.mastery.scheduleManagementJPA.exception.customException;

import assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class OpenApiException extends RuntimeException{
    private final ExceptionCode exceptionCode;

    public OpenApiException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
