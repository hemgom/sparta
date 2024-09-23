package numbersBaseball.exception;

/**
 * enum 클래스 상수 조회시 조건을 만족하는 상수가 존재하지 않을 때 사용되는 예외 클래스
 */
public class NotExistConstantsException extends Exception {
    public NotExistConstantsException(String message) {
        super(message);
    }
}
