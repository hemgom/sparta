package numbersBaseball.domain.generateCorrectAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// CorrectAnswerGenerator 인터페이스를 구현한 클래스
public class CorrectAnswerGeneratorImpl implements CorrectAnswerGenerator {
    private final List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    private String correctAnswer;

    @Override
    public void makeCorrectAnswer() {

        StringBuilder result = new StringBuilder();

        Collections.shuffle(numbers);
        for (int i = 0; i < 3; i++) {
            result.append(numbers.get(i));
        }

        correctAnswer = result.toString();
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
