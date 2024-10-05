package numbersBaseball.domain.generateCorrectAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CorrectAnswerGeneratorImpl implements CorrectAnswerGenerator {
    private final List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    @Override
    public String makeCorrectAnswer(int digits) {

        StringBuilder result = new StringBuilder();

        Collections.shuffle(numbers);
        for (int i = 0; i < digits; i++) {
            result.append(numbers.get(i));
        }

        return result.toString();
    }
}
