package assigment.mastery.scheduleManagementJPA.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    NOT_FOUND_SCHEDULE(HttpStatus.NOT_FOUND, "Schedule not found"),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "Comment not found"),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "Member not found"),
    HAS_NOT_PERMISSION(HttpStatus.UNAUTHORIZED, "You do not have permission"),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "Email address already in use"),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "Password does not match"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ExceptionCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
