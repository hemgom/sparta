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
        // 게임기록 초기화 및 '게임 시작하기' 메시지 출력
        gameRecorder.resetGameRecord();
        System.out.println(MENU.getMessage());

        // '시작 메뉴' 출력 및 메뉴 선택(선택에 따라 시스템 메시지 출력)
        // 유효하지 않은 '메뉴 번호' 를 입력 받았다면 입력과정 반복
        String menuNumber;  // 사용자가 선택한 '메뉴 번호' 저장
        do {
            menuNumber = scanner.nextLine();
        } while (!inputValidator.isValidMenuNumber(menuNumber));   // 입력 받은 값이 유효한 '메뉴 번호' 인지 검증

        // '0. 자리수 설정' 메뉴 선택시 수행
        if (menuNumber.equals(MENU_NUM_ZERO.getCriteria())) {
            System.out.println(PLEASE_INPUT_NUMBER_LENGTH.getMessage());

            // 사용자가 선택한 '정답 길이'
            int selectedNumberLength = selectNumberLength();
            
            System.out.printf(PLEASE_SETTING_NUMBER_LENGTH.getMessage(), selectedNumberLength);
            System.out.println(GAME_START.getMessage());
            
            // 설정한 '정답 길이' 로 게임 시작
            playGame(selectedNumberLength);
            
            return true;
        }

        // '1. 게임 시작하기' 메뉴 선택시 수행
        if (menuNumber.equals(MENU_NUM_ONE.getCriteria())) {
            System.out.println(GAME_START.getMessage());

            // 기본 '정답 길이' 로 게임 시작
            playGame(defaultNumberLength);

            return true;
        }

        // '2. 게임 기록보기' 메뉴 선택시 수행
        if (menuNumber.equals(MENU_NUM_TWO.getCriteria())) {
            System.out.println(VIEW_GAME_RECORD.getMessage());

            // 현재까지 저장된 '게임 플레이 기록' 들 가져오기
            List<Integer> gameRecords = gameRecorder.getGameRecords();

            // 저장된 '게임 플레이 기록' 이 없다면 해당되는 시스템 메시지 출력
            if (gameRecords.isEmpty()) {
                System.out.println(NO_SAVED_GAME_RECORD.getMessage());
                return true;
            }

            // 저장된 '게임 플레이 기록' 이 있다면 저장된 기록들을 순차적으로 출력
            for (int i = 0; i < gameRecords.size(); i++) {
                System.out.printf(GAME_RECORD.getMessage(), i, gameRecords.get(i));
            }

            return true;
        }

        // '3. 종료하기' 메뉴 선택시 수행
        if (menuNumber.equals(MENU_NUM_THREE.getCriteria())) {
            // 종료전 저장된 모든 게임 기록들을 삭제(초기화)
            gameRecorder.clearAllGameRecords();

            // application 이 종료되어 더 이상 '입력' 이 없으므로 Scanner 닫기
            scanner.close();

            return false;
        }

        return true;
    }

    /**
     * 숫자 야구 게임 시작
     */
    private void playGame(int correctAnswerLength) {
        // 파라미터로 전달받은 '정답 길이' 로 정답 생성
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

    /**
     * 게임의 '정답 길이' 를 선택하는 기능을 수행
     */
    private int selectNumberLength() {
        int selectedNumberLength;

        while (true) {
            String numberLength = scanner.nextLine();

            // 사용자가 입력한 값이 유효한지 검증
            if (!inputValidator.isValidNumberLength(numberLength)) continue;

            // 입력 값(String)을 'int' 타입으로 파싱
            selectedNumberLength = Integer.parseInt(numberLength);

            break;
        }

        return selectedNumberLength;
    }
}
