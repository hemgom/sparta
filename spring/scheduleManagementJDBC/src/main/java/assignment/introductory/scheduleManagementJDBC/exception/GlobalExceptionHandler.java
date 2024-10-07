package assignment.introductory.scheduleManagementJDBC.exception;

import assignment.introductory.scheduleManagementJDBC.exception.customException.NotFoundSearchInfoException;
import assignment.introductory.scheduleManagementJDBC.exception.customException.NotMatchedPasswordException;
import assignment.introductory.scheduleManagementJDBC.exception.customException.NotValidRequestDataException;
import assignment.introductory.scheduleManagementJDBC.exception.exceptionCode.ExceptionCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static assignment.introductory.scheduleManagementJDBC.exception.exceptionCode.ExceptionCode.HTTP_MESSAGE_MISSING_REQUEST_BODY;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundSearchInfoException.class)
    public ResponseEntity<Object> handleNotMatchedConstantException(NotFoundSearchInfoException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        return ResponseEntity.status(exceptionCode.getHttpStatus()).body(exceptionCode.getMessage());
    }

    @ExceptionHandler(NotValidRequestDataException.class)
    public ResponseEntity<Object> handleNotValidRequestData(NotValidRequestDataException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        return ResponseEntity.status(exceptionCode.getHttpStatus()).body(e.getFieldName() + exceptionCode.getMessage());
    }

    @ExceptionHandler(NotMatchedPasswordException.class)
    public ResponseEntity<Object> handleNotMatchedPasswordException(NotMatchedPasswordException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        return ResponseEntity.status(exceptionCode.getHttpStatus()).body(exceptionCode.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException() {
        ExceptionCode exceptionCode = HTTP_MESSAGE_MISSING_REQUEST_BODY;
        return ResponseEntity.status(exceptionCode.getHttpStatus()).body(exceptionCode.getMessage());
    }
}