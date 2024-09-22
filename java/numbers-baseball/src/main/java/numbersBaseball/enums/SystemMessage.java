package numbersBaseball.enums;

// application 에서 사용할 시스템 메시지를 필드로 갖는 상수를 열거한 enum 클래스
public enum SystemMessage {
    START_MENU(
            "환영합니다! 아래의 메뉴를 확인하고 원하는 메뉴 번호를 입력해주세요.\n" +
            "0.자리수 설정 | 1.게임 시작하기 | 2.게임 기록 보기 | 3.종료하기"
    ),
    PLEASE_INPUT_LENGTH("설정하고자 하는 자리수를 입력하세요."),
    LENGTH_SETTING_COMPLETE("%d 자리수 난이도로 설정되었습니다.\n"),
    GAME_START("< 게임을 시작합니다 >"),
    PLEASE_INPUT_NUMBERS("숫자를 입력해주세요."),
    CONGRATULATION(
            "축하합니다! 정답입니다~\n" +
            "=============================="
    ),
    COMPARE_RESULT(
            "결과 : %d 스트라이크, %d 볼, %d 아웃\n" +
            "=============================="
    ),
    VIEW_GAME_RECORD("< 게임 기록 보기 >"),
    NO_SAVED_GAME_RECORD(
            "저장된 게임 기록이 존재하지 않습니다.\n" +
            "=============================="
    ),
    GAME_RECORD("%d번째 게임 : 시도 횟수 - %d\n"),
    ;

    private final String message;

    SystemMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
