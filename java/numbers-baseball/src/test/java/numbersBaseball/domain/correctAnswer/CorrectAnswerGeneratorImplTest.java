package numbersBaseball.domain.correctAnswer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ThreeDigitNumberTest {


    @Test
    @DisplayName("정답 생성 테스트")
    void makeCorrectAnswerTest() {

        //given
        ThreeDigitNumber threeDigitNumber = new ThreeDigitNumber();

        //when
        threeDigitNumber.makeCorrectAnswer();

        //then
        System.out.println(threeDigitNumber.getCorrectAnswer());
    }

    @Test
    @DisplayName("정답 랜덤 테스트")
    void checkRandom() {

        //given
        ThreeDigitNumber threeDigitNumber = new ThreeDigitNumber();
        List<String> answers = new ArrayList<>();

        //when
        for (int i = 0; i < 20; i++) {
            threeDigitNumber.makeCorrectAnswer();
            answers.add(threeDigitNumber.getCorrectAnswer());
        }

        //then
        System.out.println(answers);
    }

}