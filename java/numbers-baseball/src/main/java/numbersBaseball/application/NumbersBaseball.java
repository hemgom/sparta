package numbersBaseball.application;

import numbersBaseball.domain.correctAnswer.CorrectAnswerGenerator;
import numbersBaseball.domain.correctAnswer.CorrectAnswerGeneratorImpl;
import numbersBaseball.domain.dataCompare.CorrectAnswerComparator;
import numbersBaseball.domain.dataCompare.CorrectAnswerComparatorImpl;
import numbersBaseball.domain.playerInput.InputValidator;
import numbersBaseball.domain.playerInput.InputValidatorImpl;

import java.util.Scanner;

public class NumbersBaseball {
    private final CorrectAnswerGenerator correctAnswerGenerator = new CorrectAnswerGeneratorImpl();
    private final InputValidator inputValidator = new InputValidatorImpl();
    private final CorrectAnswerComparator correctAnswerComparator = new CorrectAnswerComparatorImpl();

    public void start(Scanner scanner) {

        correctAnswerGenerator.makeCorrectAnswer();
        String correctAnswer = correctAnswerGenerator.getCorrectAnswer();

        while (true) {
            // 사용자 입력
            String playerInput = scanner.nextLine();

            // 입력 값 유효성 검증
            if (!inputValidator.isValidInput(playerInput)) continue;

            // 정답과 입력 값 비교
            correctAnswerComparator.compareCorrectAnswer(correctAnswer, playerInput);
            if (correctAnswerComparator.getStrike() == 3) {
                System.out.println("축하합니다! 정답입니다!");
                break;
            } else {
                System.out.println(
                        "결과 : " +
                                correctAnswerComparator.getStrike() + "스트라이크, " +
                                correctAnswerComparator.getBall() + "볼, " +
                                correctAnswerComparator.getOut() + "아웃");
            }
        }
    }
}
