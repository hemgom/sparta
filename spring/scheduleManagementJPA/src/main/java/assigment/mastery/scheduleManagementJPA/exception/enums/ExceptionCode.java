package assigment.mastery.scheduleManagementJPA.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    NOT_FOUND_SCHEDULE(HttpStatus.NOT_FOUND, "Schedule not found"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ExceptionCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
