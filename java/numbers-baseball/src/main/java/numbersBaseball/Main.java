package numbersBaseball;

import numbersBaseball.application.NumbersBaseballApp;

public class Main {
    public static void main(String[] args) {
        // NumberBaseballApp 객체를 생성, start() 메서드 호출
        // start() 메서드의 반환 값을 통해 반복 호출 여부를 확인함
        NumbersBaseballApp numbersBaseballApp = new NumbersBaseballApp();
        boolean reStart;
        do {
            reStart = numbersBaseballApp.start();
        } while (reStart);

        // 실행 정상 종료
        System.exit(0);
    }
}