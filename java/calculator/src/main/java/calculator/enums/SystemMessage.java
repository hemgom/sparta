package calculator.enums;

// '열거형 클래스' enum 을 활용한 시스템메시지 설정
public enum SystemMessage {
    SELECT_CALCULATOR(
            "사용할 계산기의 레벨을 입력\n" +
            "계산기 목록 : 1 - level01, 2 - level02, 3 - level03, 4 - level04"
    ),
    ERROR_SELECT_CALCULATOR(
            "계산기 선택 오류 : 사용할 계산기의 레벨을 입력\n" +
            "사용 가능 계산기 : 1 - level01, 2 - level02, 3 - level03, 4 -level04"
    ),
    THREE_INPUT_GUIDE("연산 입력은 '피연산자 > 연산자 > 피연산자' 순으로 입력해주세요."),
    ONE_INPUT_GUIDE("연산 입력은 하나의 사칙연산을 공백으로 구분하여 입력해주세요. ex) 5 + 1, 5 * -5"),
    ERROR_INPUT_OPERAND("피연산자 입력 오류 : 입력 가능한 피연산자는 정수 또는 실수입니다."),
    ERROR_INPUT_OPERATOR("연산자 입력 오류 : 입력 가능한 연산자는 '+', '-', '*', '/' 입니다."),
    ERROR_INPUT_CALCULATION("연산 입력 오류 : 사칙연산 하나를 연산형식에 맞춰 공백으로 구분해 입력해주세요. ex) 3 + 2, 5 * -2.5"),
    CALCULATE_RESULT("연산 결과 : "),
    INPUT_RETRY_PLEASE("처음부터 다시 입력해주세요."),
    RECENTLY_RESULTS("최근 계산 결과 : "),
    BIGGER_RESULTS("현재 연산결과보다 큰 결과들 : "),
    RESULT_WINDOW_DIVIDER("==========================="),
    CALCULATOR_EXIT("계산기를 종료합니다."),
    ;

    private final String message;

    SystemMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}