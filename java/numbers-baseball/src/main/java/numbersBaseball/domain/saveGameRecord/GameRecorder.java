package numbersBaseball.domain.saveGameRecord;

import java.util.List;

/**
 * '게임 기록 저장' 역할을 가지는 클래스가 구현해야 하는 인터페이스
 */
public interface GameRecorder {
    /**
     * 이전 게임의 기록을 초기화하는 기능 수행
     */
    void resetGameRecord();

    /**
     * 정답을 맞추기 위한 입력 시도의 횟수를 증가시키는 기능 수행
     */
    void addTryCount();

    /**
     * 게임기록을 저장하는 기능 수행
     */
    void saveGameRecord();

    /**
     * application 실행 후 현재까지 플레이한 게임기록들을 반환하는 기능 수행
     */
    List<Integer> getGameRecords();
    
    /**
     * 저장된 모든 게임기록들을 초기화하는 기능 수행
     */
    void clearAllGameRecords();
}
