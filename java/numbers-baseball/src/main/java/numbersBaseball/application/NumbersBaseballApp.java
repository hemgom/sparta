package numbersBaseball.application;

import numbersBaseball.domain.checkCorrectAnswer.CorrectAnswerComparator;
import numbersBaseball.domain.checkCorrectAnswer.CorrectAnswerComparatorImpl;
import numbersBaseball.domain.checkCorrectAnswer.dto.ComparedResult;
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

/**
 * '숫자 야구' 게임
 * start() 메서드를 통해 게임을 실행할 수 있음
 */
public class NumbersBaseballApp {
    // application 수행을 위해 필요한 객체들, 각 인터페이스의 구현 클래스 객체를 사용함
    private final CorrectAnswerGenerator correctAnswerGenerator = new CorrectAnswerGeneratorImpl();  // 정답 생성기
    private final InputValidator inputValidator = new InputValidatorImpl(); // 입력 검증기
    private final CorrectAnswerComparator correctAnswerComparator = new CorrectAnswerComparatorImpl();  // 정답 비교기
    private final GameRecorder gameRecorder = new GameRecorderImpl();   // 게임 기록기(계)

    // 입력 값을 받기 위해 사용한 Scanner 객체
    private final Scanner scanner = new Scanner(System.in);

    // 기본 '정답 길이' 를 지정
    private final int DEFAULT_NUMBER_LENGTH = Integer.parseInt(DEFAULT_LENGTH.getCriteria());

    /**
     * NumbersBaseball application 실행 기능 수행
     */
    public boolean start() {
        gameRecorder.resetGameRecord();

        System.out.println(MENU.getMessage());
        String menuNumber = selectMenu();

        boolean continueGame = true;
        if (menuNumber.equals(MENU_NUM_ZERO.getCriteria())) {
            System.out.println(PLEASE_INPUT_NUMBER_LENGTH.getMessage());

            int selectedNumberLength = selectNumberLength();

            System.out.printf(PLEASE_SETTING_NUMBER_LENGTH.getMessage(), selectedNumberLength);
            System.out.println(GAME_START.getMessage());

            playGame(selectedNumberLength);

        } else if (menuNumber.equals(MENU_NUM_ONE.getCriteria())) {
            System.out.println(GAME_START.getMessage());

            playGame(DEFAULT_NUMBER_LENGTH);

        } else if (menuNumber.equals(MENU_NUM_TWO.getCriteria())) {
            System.out.println(VIEW_GAME_RECORD.getMessage());

            gameRecorder.printGameRecords();

        } else if (menuNumber.equals(MENU_NUM_THREE.getCriteria())) {
            gameRecorder.clearAllGameRecords();

            scanner.close();

            continueGame = false;
        }

        return continueGame;
    }

    private String selectMenu() {
        String selectedMenuNum;
        do {
            selectedMenuNum = scanner.nextLine();
        } while (!inputValidator.isValidMenuNumber(selectedMenuNum));

        return selectedMenuNum;
    }

    private int selectNumberLength() {
        int selectedNumberLength;

        while (true) {
            String numberLength = scanner.nextLine();

            if (inputValidator.isValidNumberLength(numberLength)) {
                selectedNumberLength = Integer.parseInt(numberLength);
                break;
            }
        }

        return selectedNumberLength;
    }

    /**
     * 숫자 야구 게임 시작
     */
    private void playGame(int correctAnswerLength) {
        String correctAnswer = correctAnswerGenerator.makeCorrectAnswer(correctAnswerLength);

        boolean isNotClear = true;
        while (isNotClear) {
            System.out.println(PLEASE_INPUT_NUMBERS.getMessage());

            String userAnswer = scanner.nextLine();
            if (!inputValidator.isValidInput(userAnswer, correctAnswer.length())) continue;

            gameRecorder.addTryCount();
            ComparedResult comparedResult = correctAnswerComparator.compareCorrectAnswer(correctAnswer, userAnswer);

            if (comparedResult.isCorrectAnswer(correctAnswerLength)) {
                System.out.println(ANSWER_CORRECTLY.getMessage());

                gameRecorder.saveGameRecord();

                isNotClear = false;

            } else {
                comparedResult.printResult();
            }
        }
    }
}
