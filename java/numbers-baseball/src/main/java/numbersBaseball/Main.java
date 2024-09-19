package numbersBaseball;

import numbersBaseball.application.NumbersBaseball;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        NumbersBaseball numbersBaseball = new NumbersBaseball();
        while (true) {
            System.out.println("환영합니다! 원하시는 번호를 입력해주세요.");
            System.out.println("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
            String choiceNum = scanner.nextLine();

            if (choiceNum.equals("3")) break;

            if (choiceNum.equals("1")) numbersBaseball.start(scanner);
        }

        scanner.close();
        System.exit(0);
    }
}