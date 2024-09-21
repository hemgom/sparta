package numbersBaseball.domain.dataCompare;

// CorrectAnswerComparator 인터페이스를 구현한 클래스
public class CorrectAnswerComparatorImpl implements CorrectAnswerComparator {
    private int strike;
    private int ball;
    private int out;

    @Override
    public void compareCorrectAnswer(String correctAnswer, String input) {
        char correctAnswerElement, inputElement;
        int s = 0, b = 0, o = 0;

        for (int i = 0; i < correctAnswer.length(); i++) {
            correctAnswerElement = correctAnswer.charAt(i);
            inputElement = input.charAt(i);

            if (correctAnswerElement == inputElement) {
                s++;
                continue;
            }

            if (correctAnswer.contains(Character.toString(inputElement))) {
                b++;
            } else {
                o++;
            }
        }

        strike = s;
        ball = b;
        out = o;
    }

    @Override
    public int getStrike() {
        return strike;
    }

    @Override
    public int getBall() {
        return ball;
    }

    @Override
    public int getOut() {
        return out;
    }
}
