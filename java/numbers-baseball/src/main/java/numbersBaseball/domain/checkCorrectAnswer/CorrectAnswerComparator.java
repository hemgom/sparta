package numbersBaseball.domain.checkCorrectAnswer;

/**
 * 정답과 사용자 입력 값을 비교하는 역할을 가지는 클래스가 구현해야 할 인터페이스
 */
public interface CorrectAnswerComparator {
    /**
     * 정답과 사용자 입력 값을 비교하는 기능 수행
     */
    void compareCorrectAnswer(String correctAnswer, String input);

    /**
     * '스트라이크' 의 개수를 반환하는 기능 수행
     */
    int getStrike();

    /**
     * '볼' 의 개수를 반환하는 기능 수행
     */
    int getBall();

    /**
     * '아웃' 의 개수를 반환하는 기능 수행
     */
    int getOut();
}
