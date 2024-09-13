package numbersBaseball;

import numbersBaseball.application.NumbersBaseball;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        NumbersBaseball numbersBaseball = new NumbersBaseball();
        while (true) {
            boolean playContinue = numbersBaseball.start(scanner);
            if (!playContinue) break;
        }

        scanner.close();
        System.exit(0);
    }
}