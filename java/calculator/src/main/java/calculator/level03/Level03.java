package calculator.level03;

import calculator.level03.calculation.Calculator;
import calculator.level03.calculation.OperationMapper;
import calculator.level03.validation.CalculationResultValidator;
import calculator.level03.validation.InputValidator;

import java.util.Scanner;

import static calculator.enums.SystemMessage.*;

// Level03 계산기의 수행 흐름 로직을 작성한 클래스
public class Level03 {
    // 실수 값을 받을 수 있어야 하므로 Double 타입의 Calculator 객체 생성
    private final Calculator<Double> cal = new Calculator<>();
    private final InputValidator valid = new InputValidator();
    private final OperationMapper<Double> operationMapper = new OperationMapper<>();
    private final CalculationResultValidator calculationResultValidator = new CalculationResultValidator();

    // 연산을 계속 수행할 경우 true 반환, 종료할 경우 false 반환
    public boolean start(Scanner sc) {

        // 입력 값들이 종료 명령어인지 확인, 입력마다 유효성을 확인해 즉시 계산기가 종료될 수 있음
        String firstInput = sc.nextLine();
        if (valid.isExitCommand(firstInput)) return false;
        String secondInput = sc.nextLine();
        if (valid.isExitCommand(secondInput)) return false;
        String thirdInput = sc.nextLine();
        if (valid.isExitCommand(thirdInput)) return false;

        // 입력 연산 유효성 검사, 입력 값들의 유효성 한 번에 확인
        if (!valid.isValidCalculation(firstInput, secondInput, thirdInput)) return true;

        // 모든 유효성 검사가 끝난 입력 값들을 Calculator 객체에 저장
        cal.setFirstNum(Double.parseDouble(firstInput));
        cal.setSecondNum(Double.parseDouble(thirdInput));

        // OperationMapper 객체의 Collection 필드에 연산자 및 연산객체 저장
        operationMapper.addOperation(secondInput);

        // 연산 수행 및 연산결과 추가
        double result = cal.calculate(operationMapper.getOperation(secondInput));
        if (!calculationResultValidator.isValidCalculationResult(result)) return true;  // 연산 결과 유효성 검증
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
