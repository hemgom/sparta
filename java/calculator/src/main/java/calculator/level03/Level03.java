package calculator.level03;

import calculator.level02.calculate.Calculator;
import calculator.level03.validation.ValidInputData;

import java.util.Scanner;

import static calculator.level03.enums.SystemMessage.*;

// '도전기능(Lv.3)' 의 요구사항을 적용해 구현한 계산기의 수행 흐름을 구현한 클래스
public class Level03 {

    private final Calculator cal = new Calculator();    // level02 계산기에서 구현한 Calculator 클래스 재사용
    private final ValidInputData valid = new ValidInputData();  // level03 패키지경로의 enum 을 활용한 ValidInputData 클래스

    // 연산을 계속 수행할 경우 true 반환, 종료할 경우 false 반환
    public boolean start(Scanner sc) {

        // 입력 값들이 종료 명령어인지 확인, 입력마다 유효성을 확인해 즉시 계산기가 종료될 수 있음
        String firstInput = sc.nextLine();
        if (valid.validExit(firstInput)) return false;
        String secondInput = sc.nextLine();
        if (valid.validExit(secondInput)) return false;
        String thirdInput = sc.nextLine();
        if (valid.validExit(thirdInput)) return false;

        // 입력 연산 유효성 검사, 입력 값들의 유효성 한 번에 확인
        if (!valid.validCalculation(firstInput, secondInput, thirdInput)) return true;

        // 모든 유효성 검사가 끝난 입력 값들을 Calculator 객체에 저장
        cal.setFirstNum(firstInput);
        cal.setOperation(secondInput);
        cal.setSecondNum(thirdInput);

        // 연산 수행 및 연산결과 추가
        double result = cal.calculate();
        System.out.println(CALCULATE_RESULT.getMessage() + result);

        // 연산결과 추가 및 최근 연산결과 출력
        cal.saveResult(result);
        System.out.println(
                RECENTLY_RESULTS.getMessage() + cal.getCalculateResults() + "\n" +
                RESULT_WINDOW_DIVIDER.getMessage()
        );

        return true;
    }

}
