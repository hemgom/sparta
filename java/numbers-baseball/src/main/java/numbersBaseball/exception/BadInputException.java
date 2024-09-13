package numbersBaseball.exception;

// 잘못된 입력에 대한 예외 클래스
public class BadInputException extends Exception {

    public BadInputException(int length) {
        super("입력 값은 " + length + "자리의 서로 다른 숫자로 구성되어야 합니다.");
    }
}
