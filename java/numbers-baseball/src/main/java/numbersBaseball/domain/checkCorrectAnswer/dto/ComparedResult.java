package numbersBaseball.domain.checkCorrectAnswer.dto;

import static numbersBaseball.enums.SystemMessage.COMPARISON_RESULT;

public class ComparedResult {
    private final int STRIKE;
    private final int BALL;
    private final int OUT;

    public ComparedResult(int strike, int ball, int out) {
        this.STRIKE = strike;
        this.BALL = ball;
        this.OUT = out;
    }

    public boolean isCorrectAnswer(int correctAnswerLength) {
        return STRIKE == correctAnswerLength;
    }

    public void printResult() {
        System.out.printf(
                COMPARISON_RESULT.getMessage() + "\n",
                STRIKE, BALL, OUT
        );
    }
}
