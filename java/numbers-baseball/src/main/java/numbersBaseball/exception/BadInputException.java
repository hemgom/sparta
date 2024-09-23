package numbersBaseball.exception;

/**
 * 입력 값에 대한 예외를 다룰 때 사용되는 예외 클래스
 */
public class BadInputException extends Exception {
    public BadInputException(String message) {
        super(message);
    }
}
