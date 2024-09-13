package numbersBaseball.domain.playerInput;

// 플레이어(사용자)의 입력 값을 검증하는 역할을 가진 클래스가 구현할 인터페이스
public interface InputValidator {
    // 입력 유효성 검증을 수행하는 메서드
    boolean isValid(String input);
}
