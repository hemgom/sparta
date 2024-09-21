package numbersBaseball.application;

import numbersBaseball.domain.correctAnswer.CorrectAnswerGenerator;
import numbersBaseball.domain.correctAnswer.CorrectAnswerGeneratorImpl;
import numbersBaseball.domain.dataCompare.CorrectAnswerComparator;
import numbersBaseball.domain.dataCompare.CorrectAnswerComparatorImpl;
import numbersBaseball.domain.playRecord.GameRecorder;
import numbersBaseball.domain.playRecord.GameRecorderImpl;
import numbersBaseball.domain.playerInput.InputValidator;
import numbersBaseball.domain.playerInput.InputValidatorImpl;

import java.util.Queue;
import java.util.Scanner;

import static numbersBaseball.enums.SystemMessage.*;
import static numbersBaseball.enums.ValidCriteria.*;

public class NumbersBaseballApp {
    private final CorrectAnswerGenerator correctAnswerGenerator = new CorrectAnswerGeneratorImpl();
    private final InputValidator inputValidator = new InputValidatorImpl();
    private final CorrectAnswerComparator correctAnswerComparator = new CorrectAnswerComparatorImpl();
    private final GameRecorder gameRecorder = new GameRecorderImpl();

    public boolean start(Scanner scanner) {
        gameRecorder.playNewGame();
        System.out.println(START_MENU.getMessage());

        String menuNum;
        do {
            menuNum = scanner.nextLine();
        } while (!inputValidator.isValidMenuNum(menuNum));

        if (menuNum.equals(FIRST_MENU_NUM.getCriteria())) {
            System.out.println(GAME_START.getMessage());
            play(scanner);
            return true;
        }

        if (menuNum.equals(SECOND_MENU_NUM.getCriteria())) {
            Queue<Integer> allRecord = gameRecorder.getGameRecords();

            System.out.println("< 게임 기록 보기 >");
            int playCount = 1;
            while (!allRecord.isEmpty()) {
                System.out.printf("%d번째 게임 : 시도 횟수 - %d\n", playCount++, allRecord.poll());
            }

            return true;
        }

        if (menuNum.equals(THIRD_MENU_NUM.getCriteria())) {
            gameRecorder.clearGameRecords();
            return false;
        }

        return true;
    }

    private void play(Scanner scanner) {
        correctAnswerGenerator.makeCorrectAnswer();
        String correctAnswer = correctAnswerGenerator.getCorrectAnswer();

        while (true) {
            System.out.println(PLEASE_INPUT_NUMBERS.getMessage());

            // 사용자 입력
            String playerInput = scanner.nextLine();

            // 입력 값 유효성 검증
            if (!inputValidator.isValidInput(playerInput)) continue;

            // 정답과 입력 값 비교
            correctAnswerComparator.compareCorrectAnswer(correctAnswer, playerInput);
            gameRecorder.guessCorrectAnswer();
            if (correctAnswerComparator.getStrike() == 3) {
                System.out.println(CONGRATULATION.getMessage());
                gameRecorder.saveRecord();
                break;
            } else {
                System.out.printf(
                        COMPARE_RESULT.getMessage() + "\n",
                        correctAnswerComparator.getStrike(),
                        correctAnswerComparator.getBall(),
                        correctAnswerComparator.getOut()
                );
            }
        }
    }
}
