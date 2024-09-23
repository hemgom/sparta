package numbersBaseball.domain.validateUserInput;

/**
 * '사용자 입력 검증' 역할을 가지는 클래스가 구현해야 하는 인터페이스
 */
public interface InputValidator {
    /**
     * 입력 받은 '정답 길이(자리수)' 의 유효성 검증 수행
     */
    boolean isValidNumberLength(String numberLength);

    /**
     * 입력 받은 '메뉴 번호' 의 유효성 검증 수행
     */
    boolean isValidMenuNumber(String menuNum);

    /**
     * 정답을 맞추기 위해 입력한 값의 유효성 검증 수행
     */
    boolean isValidInput(String input, int correctAnswerLength);
}
