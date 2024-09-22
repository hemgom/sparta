package numbersBaseball.domain.validateUserInput;

// 플레이어(사용자)의 입력 값을 검증하는 역할을 가질 클래스가 구현할 인터페이스
public interface InputValidator {
    // 입력 값의 유효성을 검증하는 메서드
    boolean isValidInput(String input, int correctAnswerLength);

    // 메뉴 번호의 유효성을 검증하는 메서드
    boolean isValidMenuNum(String menuNum);

    // 난이도 선택의 유효성을 검증하는 메서드
    boolean isValidDifficultyLevel(String difficultyLevel);
}
