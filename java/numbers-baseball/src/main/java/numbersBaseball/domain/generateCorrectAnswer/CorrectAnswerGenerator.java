package numbersBaseball.domain.generateCorrectAnswer;

// 정답을 생성하는 역할을 가질 클래스가 구현할 인터페이스
public interface CorrectAnswerGenerator {
    // 정답을 생성하는 메서드
    void makeCorrectAnswer(int digits);

    // 생성된 정답을 반환하는 메서드
    String getCorrectAnswer();
}
