package numbersBaseball.exception;

// 잘못된 입력에 대한 예외 클래스
public class BadInputException extends Exception {

    public BadInputException(String message) {
        super(message);
    }
}
