package assignment.introductory.scheduleManagement.exception;

import assignment.introductory.scheduleManagement.exception.customException.NotMatchedConstantException;
import assignment.introductory.scheduleManagement.exception.customException.NotValidRequestDataException;
import assignment.introductory.scheduleManagement.exception.exceptionCode.ExceptionCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotMatchedConstantException.class)
    public ResponseEntity<Object> handleNotMatchedConstantException(NotMatchedConstantException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        return ResponseEntity.status(exceptionCode.getHttpStatus()).body(exceptionCode.getMessage());
    }

    @ExceptionHandler(NotValidRequestDataException.class)
    public ResponseEntity<Object> handleNotValidRequestData(NotValidRequestDataException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        return ResponseEntity.status(exceptionCode.getHttpStatus()).body(e.getFieldName() + exceptionCode.getMessage());
    }
}