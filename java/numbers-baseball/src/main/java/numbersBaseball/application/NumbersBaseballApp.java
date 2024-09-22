package numbersBaseball.application;

import numbersBaseball.domain.checkCorrectAnswer.CorrectAnswerComparator;
import numbersBaseball.domain.checkCorrectAnswer.CorrectAnswerComparatorImpl;
import numbersBaseball.domain.generateCorrectAnswer.CorrectAnswerGenerator;
import numbersBaseball.domain.generateCorrectAnswer.CorrectAnswerGeneratorImpl;
import numbersBaseball.domain.saveGameRecord.GameRecorder;
import numbersBaseball.domain.saveGameRecord.GameRecorderImpl;
import numbersBaseball.domain.validateUserInput.InputValidator;
import numbersBaseball.domain.validateUserInput.InputValidatorImpl;

import java.util.List;
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

        int choiceLength = Integer.parseInt(DEFAULT_LENGTH.getCriteria());

        if (menuNum.equals(MENU_NUM_ZERO.getCriteria())) {
            System.out.println(PLEASE_INPUT_LENGTH.getMessage());

            while (true) {
                String difficultyLevel = scanner.nextLine();
                if (!inputValidator.isValidDifficultyLevel(difficultyLevel)) continue;
                choiceLength = Integer.parseInt(difficultyLevel);
                break;
            }

            System.out.printf(LENGTH_SETTING_COMPLETE.getMessage(), choiceLength);
            System.out.println(GAME_START.getMessage());
            play(scanner, choiceLength);
            return true;
        }

        if (menuNum.equals(MENU_NUM_ONE.getCriteria())) {
            System.out.println(GAME_START.getMessage());
            play(scanner, choiceLength);
            return true;
        }

        if (menuNum.equals(MENU_NUM_TWO.getCriteria())) {
            System.out.println(VIEW_GAME_RECORD.getMessage());

            List<Integer> gameRecords = gameRecorder.getGameRecords();

            if (gameRecords.isEmpty()) {
                System.out.println(NO_SAVED_GAME_RECORD.getMessage());
                return true;
            }

            for (int i = 0; i < gameRecords.size(); i++) {
                System.out.printf(GAME_RECORD.getMessage(), i, gameRecords.get(i));
            }

            return true;
        }

        if (menuNum.equals(MENU_NUM_THREE.getCriteria())) {
            gameRecorder.clearGameRecords();
            return false;
        }

        return true;
    }

    private void play(Scanner scanner, int length) {
        correctAnswerGenerator.makeCorrectAnswer(length);
        String correctAnswer = correctAnswerGenerator.getCorrectAnswer();

        while (true) {
            System.out.println(PLEASE_INPUT_NUMBERS.getMessage());

            // 사용자 입력
            String playerInput = scanner.nextLine();

            // 입력 값 유효성 검증
            if (!inputValidator.isValidInput(playerInput, correctAnswer.length())) continue;

            // 정답과 입력 값 비교
            correctAnswerComparator.compareCorrectAnswer(correctAnswer, playerInput);
            gameRecorder.guessCorrectAnswer();
            if (correctAnswerComparator.getStrike() == length) {
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
