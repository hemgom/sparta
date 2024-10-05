package numbersBaseball.domain.checkCorrectAnswer;

import numbersBaseball.domain.checkCorrectAnswer.dto.ComparedResult;

/**
 * 정답과 사용자 입력 값을 비교하는 역할을 가지는 클래스가 구현해야 할 인터페이스
 */
public interface CorrectAnswerComparator {
    /**
     * 정답과 사용자 입력 값을 비교하는 기능 수행
     */
    ComparedResult compareCorrectAnswer(String correctAnswer, String input);
}
