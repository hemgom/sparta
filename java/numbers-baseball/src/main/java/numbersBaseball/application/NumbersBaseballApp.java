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
    private final int defaultNumberLength = Integer.parseInt(DEFAULT_LENGTH.getCriteria());

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

            playGame(defaultNumberLength);

        } else if (menuNumber.equals(MENU_NUM_TWO.getCriteria())) {
            System.out.println(VIEW_GAME_RECORD.getMessage());

            List<Integer> gameRecords = gameRecorder.getGameRecords();

            if (gameRecords.isEmpty()) {
                System.out.println(NO_SAVED_GAME_RECORD.getMessage());
                return continueGame;
            }

            for (int i = 0; i < gameRecords.size(); i++) {
                System.out.printf(GAME_RECORD.getMessage(), i, gameRecords.get(i));
            }

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
        correctAnswerGenerator.makeCorrectAnswer(correctAnswerLength);
        String correctAnswer = correctAnswerGenerator.getCorrectAnswer();

        // 정답을 맞추기 위한 시도를 반복적으로 수행, 정답을 맞추면 반복 종료
        while (true) {
            System.out.println(PLEASE_INPUT_NUMBERS.getMessage());

            // 사용자가 정답이라 생각하는 값
            String userAnswer = scanner.nextLine();

            // 사용자의 입력 값 유효성 검증, 유효하지 않다면 다음 반복 수행
            if (!inputValidator.isValidInput(userAnswer, correctAnswer.length())) continue;

            // 현재 게임의 정답을 맞추기 위한 '입력 시도 횟수' 추가
            gameRecorder.addTryCount();
            
            // 생성된 정답과 사용자 입력 값을 비교
            correctAnswerComparator.compareCorrectAnswer(correctAnswer, userAnswer);

            // 정답 결과를 확인해 '스트라이크' 가 '정답 길이' 와 같을 경우(=정답을 맞춤) 반복 종료
            // 정답을 못 했다면 현재 '비교 결과' 를 출력하고 다음 반복 수행
            if (correctAnswerComparator.getStrike() == correctAnswerLength) {
                System.out.println(ANSWER_CORRECTLY.getMessage());
                
                // 정답을 맞추기까지 '입력 시도 횟수' 를 저장
                gameRecorder.saveGameRecord();

                break;

            } else {
                // 현재 비교 결과 출력
                System.out.printf(
                        COMPARISON_RESULT.getMessage() + "\n",
                        correctAnswerComparator.getStrike(),
                        correctAnswerComparator.getBall(),
                        correctAnswerComparator.getOut()
                );
            }
        }
    }
}
