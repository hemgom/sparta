package assignment.introductory.scheduleManagement.exception.exceptionCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    NOT_FOUND_SCHEDULE(HttpStatus.NOT_FOUND, "요청 정보와 일치하는 일정을 조회할 수 없습니다."),
    NOT_FOUND_SCHEDULE_LIST(HttpStatus.NOT_FOUND, "요청 정보와 일치하는 일정 목록을 조회할 수 없습니다."),
    NOT_FOUND_AUTHOR(HttpStatus.NOT_FOUND, "요청한 정보와 일치하는 작성자를 조회할 수 없습니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "패스워드가 일치하지 않습니다"),
    BAD_REQUEST_ADD_SCHEDULE(HttpStatus.BAD_REQUEST,
            """
                     값을 다시 입력해 주세요.
                    할 일 - 최대 150자, 작성자명 - 최대 20자, 패스워드 - 최대 6자
                    입력값은 모두 필수입니다."""),
    BAD_REQUEST_FIND_ALL_SCHEDULE(HttpStatus.BAD_REQUEST,
            """
                     값을 다시 입력해 주세요.
                    조회 날짜 형식 - YYYY-MM-DD, 작성자명 - 최대 20자"""),
    BAD_REQUEST_UPDATE_SCHEDULE(HttpStatus.BAD_REQUEST,
            """
                     값을 다시 입력해 주세요.
                    할 일 - 최대 150자, 작성자명 - 최대 20자
                    입력값은 모두 필수입니다."""),
    HTTP_MESSAGE_MISSING_REQUEST_BODY(HttpStatus.BAD_REQUEST, "필수 요청 본문이 누락되었습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ExceptionCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
