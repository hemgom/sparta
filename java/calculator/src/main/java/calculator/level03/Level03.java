package calculator.level03;

import calculator.level03.calculate.Calculator;
import calculator.level03.validation.ValidInput;

import java.util.Scanner;

import static calculator.level03.enums.SystemMessage.*;

// Level03 계산기의 수행 흐름 로직을 작성한 클래스
public class Level03 {
    // 실수 값을 받을 수 있어야 하므로 Double 타입의 Calculator 객체 생성
    private final Calculator<Double> cal = new Calculator<>();
    private final ValidInput valid = new ValidInput();

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
        cal.setFirstNum(Double.parseDouble(firstInput));
        cal.setOperation(secondInput);
        cal.setSecondNum(Double.parseDouble(thirdInput));

        // 연산 수행 및 연산결과 추가
        double result = cal.calculate();
        System.out.println(CALCULATE_RESULT.getMessage() + result);

        // 최근 연산결과(현재 입력 결과 포함X) 출력
        System.out.println(
                RECENTLY_RESULTS.getMessage() + cal.getCalculateResults()
        );
        // 최근 연산결과 중 현재 입력 결과보다 큰 결과들만 출력
        System.out.println(
                BIGGER_RESULTS.getMessage() + cal.getBiggerResults(result) + "\n" +
                RESULT_WINDOW_DIVIDER.getMessage()
        );
        // 현재 입력 결과, Calculator 객체 필드의 Collection 객체에 저장
        cal.saveResult(result);

        return true;
    }

}
