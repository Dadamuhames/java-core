package lessons.lesson02;

import java.util.Random;
import java.util.Scanner;

public class PracticeTasks {
    private final Scanner scanner;

    public PracticeTasks(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("Обмен значений");

        System.out.println("Введите два числа");

        swapValues();

        System.out.println("------------");

        System.out.println("Таблица умножения");

        System.out.print("Введите число:");

        printMultiplicationTable();

        System.out.println("------------");

        System.out.println("Конвертер валют");

        System.out.print("Введите сумму в долларах:");

        convertCurrency();

        System.out.println("------------");

        System.out.println("Чётное или нечётное");

        System.out.print("Введите число:");

        findEvenOrOdd();

        System.out.println("------------");

        System.out.println("Максимум из трёх");

        maximumOfThree();

        System.out.println("------------");

        System.out.println("Калькулятор");

        calculator();

        System.out.println("------------");

        System.out.println("Среднее арифметическое");

        findAvg();

        System.out.println("------------");

        System.out.println("Поиск элемента");

        System.out.println(checkArrayForNumber());

        System.out.println("------------");

        System.out.println("Мини-игра \"Угадай число\" ");

        guessTheNumber();
    }

    public void swapValues() {
        int numberOne = scanner.nextInt();
        int numberTwo = scanner.nextInt();

        System.out.println(numberTwo);
        System.out.println(numberOne);
    }

    public void printMultiplicationTable() {
        int number = scanner.nextInt();

        for (int i = 1; i < 10; i++) {
            System.out.printf("%d x %d = %d%n", number, i, number * i);
        }
    }

    public void convertCurrency() {
        int exchange = 12500;

        long money = scanner.nextLong();

        System.out.println(money * exchange);
    }

    public void findEvenOrOdd() {
        int number = scanner.nextInt();

        if (number % 2 == 0) {
            System.out.println("Чётное");
        } else {
            System.out.println("нечётное");
        }
    }

    public void maximumOfThree() {
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        if (a > b && a > c) {
            System.out.println(a);
        } else if (b > a && b > c) {
            System.out.println(b);
        } else if (c > b && c > a) {
            System.out.println(c);
        }
    }

    public void calculator() {
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.nextLine();
        String operation = scanner.nextLine();

        switch (operation) {
            case "+": {
                System.out.println(a + b);
                return;
            }

            case "-": {
                System.out.println(a - b);
                return;
            }

            case "*": {
                System.out.println(a * b);
                return;
            }

            case "/": {
                System.out.println(a / b);
            }
        }
    }

    public void findAvg() {
        String numbersAsString = scanner.nextLine();

        String[] numbersAsStringList = numbersAsString.split(", ");

        int sumOfNumbers = 0;

        for (String s : numbersAsStringList) {
            sumOfNumbers += Integer.parseInt(s);
        }

        System.out.println(sumOfNumbers / numbersAsStringList.length);
    }

    public boolean checkArrayForNumber() {
        String numbersAsString = scanner.nextLine();
        String[] numbersAsStringList = numbersAsString.split(", ");

        String numberAsString = scanner.nextLine();

        for (String number : numbersAsStringList) {
            if (number.equals(numberAsString)) {
                return true;
            }
        }

        return false;
    }

    public void guessTheNumber() {
        int number = new Random().nextInt();
        int tryCount = 3;

        while (tryCount != 0) {
            int inputNumber = scanner.nextInt();

            if (inputNumber == number) {
                System.out.println("You win!");
            }

            tryCount--;
            System.out.printf("Wrong! %d tries left! %n", tryCount);
        }
    }
}
