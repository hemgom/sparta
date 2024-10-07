package numbersBaseball.domain.saveGameRecord;

import java.util.ArrayList;
import java.util.List;

import static numbersBaseball.enums.SystemMessage.GAME_RECORD;
import static numbersBaseball.enums.SystemMessage.NO_SAVED_GAME_RECORD;

// GameRecorder 인터페이스를 구현한 클래스
public class GameRecorderImpl implements GameRecorder {
    // 한 게임에서 정답을 맞추기 위한 시도 횟수를 저장하는 변수
    private int tryCount = 0;

    // 각 게임별 정답을 맞추기까지 시도한 '입력 횟수(= 플레이 기록)' 를 저장하는 Collection 객체
    private final List<Integer> gameRecords = new ArrayList<>();

    @Override
    public void resetGameRecord() {
        if (tryCount == 0) return;      // 이전 게임 기록이 없으면 아무것도 수행하지 않음
        tryCount = 0;                   // 이전 게임 기록 초기화
    }

    @Override
    public void addTryCount() {
        tryCount++;     // 입력 시도 횟수 1 증가
    }

    @Override
    public void saveGameRecord() {
        gameRecords.add(tryCount);      // 클리어한 게임 기록을 List<Integer> gameRecords 에 저장
    }

    @Override
    public void printGameRecords() {
        if (gameRecords.isEmpty()) {
            System.out.println(NO_SAVED_GAME_RECORD.getMessage());

        } else {
            for (int i = 0; i < gameRecords.size(); i++) {
                System.out.printf(GAME_RECORD.getMessage(), i, gameRecords.get(i));
            }
        }
    }

    @Override
    public void clearAllGameRecords() {
        gameRecords.clear();    // 지금까지 저장된 모든 게임기록들을 초기화(삭제)
    }
}
