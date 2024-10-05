package numbersBaseball.domain.checkCorrectAnswer;

import numbersBaseball.domain.checkCorrectAnswer.dto.ComparedResult;

// CorrectAnswerComparator 인터페이스를 구현한 클래스
public class CorrectAnswerComparatorImpl implements CorrectAnswerComparator {
    @Override
    public ComparedResult compareCorrectAnswer(String correctAnswer, String input) {
        char correctAnswerElement, inputElement;
        int strike = 0, ball = 0, out = 0;

        for (int i = 0; i < correctAnswer.length(); i++) {
            correctAnswerElement = correctAnswer.charAt(i);
            inputElement = input.charAt(i);

            if (correctAnswerElement == inputElement) {
                strike++;
                continue;
            }

            if (correctAnswer.contains(Character.toString(inputElement))) {
                ball++;
            } else {
                out++;
            }
        }

        return new ComparedResult(strike, ball, out);
    }
}
