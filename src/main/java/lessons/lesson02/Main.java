package lessons.lesson02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Выберите программу:");
        System.out.println("1 - Базовые задания");
        System.out.println("2 - Практические задания");
        System.out.println("3 - Квест");

        Scanner scanner = new Scanner(System.in);

        String programNumber = scanner.nextLine();


        switch (programNumber) {
            case "1" -> new BaseTasks(scanner).run();

            case "2" -> new PracticeTasks(scanner).run();

            case "3" -> new Quest(scanner).run();
        }

        scanner.close();
    }
}
