package numbersBaseball.domain.generateCorrectAnswer;

/**
 * 정답을 생성하는 역할을 가지는 클래스가 구현해야 하는 인터페이스
 */
public interface CorrectAnswerGenerator {
    /**
     * 정답을 생성하는 기능 수행
     */
    String makeCorrectAnswer(int digits);
}
