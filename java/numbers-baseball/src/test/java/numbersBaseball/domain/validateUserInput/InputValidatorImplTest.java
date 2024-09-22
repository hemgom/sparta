package numbersBaseball.domain.validateUserInput;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputValidatorImplTest {

    InputValidator validator = new InputValidatorImpl();

    @Test
    @DisplayName("세 자리 수이지만 각 숫자가 중복인 경우")
    void numberButDuplicate() {
        //given
        String input = "252";

        //when
        boolean result = validator.isValidInput(input, 3);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("입력 값에 숫자가 아닌 문자가 있는 경우")
    void notNumber() {
        //given
        String input = "A12";

        //when
        boolean result = validator.isValidInput(input, 3);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("입력 값이 세 자리 이상인 경우")
    void overLength() {
        //given
        String input = "1234";

        //when
        boolean result = validator.isValidInput(input, 3);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("올바른 입력의 경우")
    void correctInput() {
        //given
        String input = "349";

        //when
        boolean result = validator.isValidInput(input, 3);

        //then
        assertThat(result).isTrue();
    }
}