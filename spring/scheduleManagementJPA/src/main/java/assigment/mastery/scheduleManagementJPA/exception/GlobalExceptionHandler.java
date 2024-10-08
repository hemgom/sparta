package assigment.mastery.scheduleManagementJPA.exception;

import assigment.mastery.scheduleManagementJPA.exception.customException.NotFoundEntityException;
import assigment.mastery.scheduleManagementJPA.exception.dto.ResponseExceptionCode;
import assigment.mastery.scheduleManagementJPA.exception.dto.NotValidRequestParameter;
import assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static assigment.mastery.scheduleManagementJPA.exception.dto.NotValidRequestParameter.NotValidParameter;
import static assigment.mastery.scheduleManagementJPA.exception.enums.ExceptionCode.INVALID_REQUEST_PARAMETER;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<Object> handleNotFoundEntityException(NotFoundEntityException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        return ResponseEntity.status(exceptionCode.getHttpStatus())
                .body(makeResponseExceptionCode(exceptionCode));
    }

    private ResponseExceptionCode makeResponseExceptionCode(ExceptionCode exceptionCode) {
        return ResponseExceptionCode.builder()
                .code(exceptionCode.name())
                .message(exceptionCode.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ExceptionCode exceptionCode = INVALID_REQUEST_PARAMETER;
        return ResponseEntity.status(exceptionCode.getHttpStatus())
                .body(makeNotValidRequestParameter(e, exceptionCode));
    }

    private NotValidRequestParameter makeNotValidRequestParameter(BindException e,
                                                                  ExceptionCode exceptionCode) {
        List<NotValidParameter> notValidParameters = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(NotValidParameter::of)
                .toList();

        return NotValidRequestParameter.builder()
                .code(exceptionCode.name())
                .message(exceptionCode.getMessage())
                .notValidParameters(notValidParameters)
                .build();
    }
}
