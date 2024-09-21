package numbersBaseball.domain.generateCorrectAnswer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CorrectAnswerGeneratorImplTest {


    @Test
    @DisplayName("정답 생성 테스트")
    void makeCorrectAnswerTest() {

        //given
        CorrectAnswerGeneratorImpl correctAnswerGeneratorImpl = new CorrectAnswerGeneratorImpl();

        //when
        correctAnswerGeneratorImpl.makeCorrectAnswer();

        //then
        System.out.println(correctAnswerGeneratorImpl.getCorrectAnswer());
    }

    @Test
    @DisplayName("정답 랜덤 테스트")
    void checkRandom() {

        //given
        CorrectAnswerGeneratorImpl correctAnswerGeneratorImpl = new CorrectAnswerGeneratorImpl();
        List<String> answers = new ArrayList<>();

        //when
        for (int i = 0; i < 20; i++) {
            correctAnswerGeneratorImpl.makeCorrectAnswer();
            answers.add(correctAnswerGeneratorImpl.getCorrectAnswer());
        }

        //then
        System.out.println(answers);
    }

}