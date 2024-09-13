package numbersBaseball.domain.correctAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 세 자리의 숫자로 이루어진 정답을 생성하는 역할
public class ThreeDigitNumber implements CorrectAnswer {
    private final List<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
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
