package numbersBaseball.domain.generateCorrectAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// CorrectAnswerGenerator 인터페이스를 구현한 클래스
public class CorrectAnswerGeneratorImpl implements CorrectAnswerGenerator {
    // 정답 생성에 필요한 요소가 저장된 List<Integer> 컬랙션 객체
    private final List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    
    // 생성한 정답이 저장되는 문자열 객체
    private String correctAnswer;

    @Override
    public void makeCorrectAnswer(int digits) {

        StringBuilder result = new StringBuilder();

        Collections.shuffle(numbers);
        for (int i = 0; i < digits; i++) {
            result.append(numbers.get(i));
        }

        correctAnswer = result.toString();
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
