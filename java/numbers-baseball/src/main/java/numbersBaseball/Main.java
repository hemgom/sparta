package numbersBaseball;

import numbersBaseball.application.NumbersBaseballApp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        NumbersBaseballApp numbersBaseballApp = new NumbersBaseballApp();
        boolean playContinue;
        do {
            playContinue = numbersBaseballApp.start(scanner);
        } while (playContinue);

        scanner.close();
        System.exit(0);
    }
}