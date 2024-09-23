package numbersBaseball.domain.checkCorrectAnswer;

// CorrectAnswerComparator 인터페이스를 구현한 클래스
public class CorrectAnswerComparatorImpl implements CorrectAnswerComparator {
    private int strike;
    private int ball;
    private int out;

    @Override
    public void compareCorrectAnswer(String correctAnswer, String input) {
        char correctAnswerElement, inputElement;
        int strikeResult = 0, ballResult = 0, outResult = 0;

        // 정답과 사용자 입력 값의 각 요소를 비교
        for (int i = 0; i < correctAnswer.length(); i++) {
            correctAnswerElement = correctAnswer.charAt(i);
            inputElement = input.charAt(i);

            // '정답' 과 '입력 값' 의 요소의 위치(index), 문자(char) 가 같다면 '스트라이크'
            if (correctAnswerElement == inputElement) {
                strikeResult++;
                continue;
            }

            // '입력 값' 의 요소를 '정답 이 포함만 하는 경우(위치는 다름) '볼'
            // '입력 값' 의 요소를 '정답' 이 포함조차 하지 않는 경우 '아웃'
            if (correctAnswer.contains(Character.toString(inputElement))) {
                ballResult++;
            } else {
                outResult++;
            }
        }

        strike = strikeResult;
        ball = ballResult;
        out = outResult;
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
