package numbersBaseball.application;

import numbersBaseball.domain.correctAnswer.ThreeDigitNumber;
import numbersBaseball.domain.playerInput.ThreeDigitValidator;

import java.util.Scanner;

public class NumbersBaseball {
    private final ThreeDigitNumber threeDigitNumber = new ThreeDigitNumber();
    private final ThreeDigitValidator threeDigitValidator = new ThreeDigitValidator();

    public boolean start(Scanner scanner) {

        threeDigitNumber.makeCorrectAnswer();
        String correct = threeDigitNumber.getCorrectAnswer();

        while (true) {
            String playerInput = scanner.nextLine();
            if (!threeDigitValidator.isValid(playerInput)) continue;
            break;
        }

        return true;
    }
}
