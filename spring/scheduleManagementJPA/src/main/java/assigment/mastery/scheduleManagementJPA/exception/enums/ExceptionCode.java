package assigment.mastery.scheduleManagementJPA.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),

    NOT_FOUND_SCHEDULE(HttpStatus.NOT_FOUND, "Schedule not found"),

    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "Comment not found"),

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "Member not found"),

    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "Email address already in use"),

    NOT_MATCH_PASSWORD(HttpStatus.UNAUTHORIZED, "Password does not match"),

    HAS_NOT_TOKEN(HttpStatus.BAD_REQUEST, "Request has not token"),

    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "Request is not valid"),

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Token has expired"),

    NOT_SUPPORT_TOKEN(HttpStatus.UNAUTHORIZED, "Is not support token"),

    HAS_NOT_PERMISSION(HttpStatus.FORBIDDEN, "You do not have permission"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ExceptionCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
