package numbersBaseball.domain.checkCorrectAnswer;

// 정답과 입력 값을 비교하는 역할을 가질 클래스가 구현할 인터페이스
public interface CorrectAnswerComparator {
    // 정답과 입력 값 비교를 수행하는 메서드
    void compareCorrectAnswer(String correctAnswer, String input);

    // 비교 결과 중 스트라이크 개수를 반환하는 메서드
    int getStrike();

    // 비교 결과 중 볼 개수를 반환하는 메서드
    int getBall();

    // 비교 결과 중 아웃 개수를 반환하는 메서드
    int getOut();
}
