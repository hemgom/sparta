package numbersBaseball.domain.saveGameRecord;

import java.util.List;

// 게임 기록을 저장(기록)하는 역할을 가질 클래스가 구현할 인터페이스
public interface GameRecorder {
    // 새 게임이 시작시 이전 게임 기록 초기화
    void playNewGame();

    // 사용자가 정답을 맞추기 위한 시도를 할 때마다 '입력 시도 횟수' 를 증가시키는 메서드
    void guessCorrectAnswer();

    // 현재 게임 기록을 저장하는 메서드
    void saveRecord();

    // 저장된 모든 게임 기록을 가져오는 메서드
    List<Integer> getGameRecords();

    // 저장된 모든 게임 기록을 초기화하는 메서드
    void clearGameRecords();
}
