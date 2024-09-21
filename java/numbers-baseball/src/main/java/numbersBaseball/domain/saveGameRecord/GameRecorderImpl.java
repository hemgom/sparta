package numbersBaseball.domain.playRecord;

import java.util.LinkedList;
import java.util.Queue;

// GameRecorder 인터페이스를 구현한 클래스
public class GameRecorderImpl implements GameRecorder {
    // 한 게임에서 정답을 맞추기 위한 시도 횟수를 저장하는 변수
    private int tryCount = 0;

    // 각 게임별 정답을 맞추기까지 시도한 '입력 횟수(= 플레이 기록)' 를 저장하는 Collection 객체
    private final Queue<Integer> gameRecords = new LinkedList<>();

    @Override
    public void playNewGame() {
        if (tryCount == 0) return;      // 이전 게임 기록이 없으면 아무것도 수행하지 않음
        tryCount = 0;                   // 이전 게임 기록 초기화
    }

    @Override
    public void guessCorrectAnswer() {
        tryCount++;
    }

    @Override
    public void saveRecord() {
        gameRecords.offer(tryCount);
    }

    @Override
    public Queue<Integer> getGameRecords() {
        return gameRecords;
    }

    @Override
    public void clearGameRecords() {
        gameRecords.clear();
    }
}
