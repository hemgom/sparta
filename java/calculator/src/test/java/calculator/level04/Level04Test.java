package calculator.level04;

import calculator.enums.Operator;
import calculator.level03.calculation.Operation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Level04Test {

    @Test
    @DisplayName("유효하지 않은 연산자가 넘어올 때")
    void findByOperatorTest() {
        //given
        String operator = " ";

        //when, then
        Operation<Double> result;
        try {
            result = Operator.findByOperator(operator);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}