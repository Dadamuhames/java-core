package lessons.lesson02;

import java.util.Scanner;

public class BaseTasks {
    private final Scanner scanner;

    public BaseTasks(final Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("Вывести числа от 1 до 100");

        printHundred();

        System.out.println("---------------------");

        System.out.println("Сумма чисел от 1 до N");
        System.out.print("Введите N:");

        int sum = getSumOfNNumbers();

        System.out.printf("Сумма: %d%n", sum);

        System.out.println("---------------------");

        System.out.println("Произведение чисел от 1 до N.");
        System.out.print("Введите N:");

        int mult = getMultiplicationOfNumbers();

        System.out.printf("Произведение: %d%n", mult);

        System.out.println("---------------------");

        System.out.println("Сумма четных чисел от 1 до N.");

        System.out.print("Введите N:");

        int sumOfEven = getSumOfEvenNNumbers();

        System.out.printf("Сумма четных чисел: %d%n", sumOfEven);

        System.out.println("---------------------");

        System.out.println("Сумма цифр числа N");

        System.out.print("Введите N:");

        int sumOfDigits = getDigitSum();

        System.out.printf("Сумма цифр числа N: %d%n", sumOfDigits);

        System.out.println("---------------------");

        System.out.println("Разворот числа N");

        System.out.print("Введите N:");

        printNumberReverse();

        System.out.println("---------------------");

        System.out.printf("первое число, которое делится на 7 и больше 1000: %d%n", getFirstDivisionBySeven());


        System.out.println("---------------------");

        System.out.println("Вывести все простые числа до N.");
        System.out.print("Введите N:");
        printPrimeNumbers();

        System.out.println("---------------------");

        System.out.println("треугольники из звездочек:");

        printTriangles();
    }

    public void printHundred() {
        for (int i = 1; i <= 100; i++) {
            System.out.printf("%d, ", i);

            if (i % 10 == 0) {
                System.out.print("\n");
            }
        }
    }

    public int getSumOfNNumbers() {
        int sum = 0;

        int n = scanner.nextInt();

        for (int i = 1; i < n; i++) {
            sum += i;
        }

        return sum;
    }

    public int getMultiplicationOfNumbers() {
        int mult = 1;

        int n = scanner.nextInt();

        for (int i = 2; i <= n; i++) {
            mult *= i;
        }

        return mult;
    }

    public int getSumOfEvenNNumbers() {
        int sum = 0;

        int n = scanner.nextInt();

        for (int i = 2; i < n; i += 2) {
            sum += i;
        }

        return sum;
    }

    public int getDigitSum() {
        int sum = 0;

        scanner.nextLine();
        String number = String.valueOf(scanner.nextInt());

        String[] digitsAsStringList = number.split("");

        for (String digitAsString : digitsAsStringList) {
            sum += Integer.parseInt(digitAsString);
        }

        return sum;
    }

    public void printNumberReverse() {
        String number = scanner.nextLine();

        StringBuilder stringBuilder = new StringBuilder(number);

        System.out.println(stringBuilder.reverse());
    }

    public int getFactorial() {
        return getMultiplicationOfNumbers();
    }

    public int getFirstDivisionBySeven() {
        int number = 1000;

        while (number % 7 != 0) {
            number++;
        }

        return number;
    }

    public boolean isPrime(int number) {
        if (number < 1) return false;

        for (int i = 2; i < number; i++) {
            if (number % i == 0) return false;
        }

        return true;
    }

    public void printPrimeNumbers() {
        int number = scanner.nextInt();

        for (int i = 2; i < number; i++) {
            if (isPrime(i)) {
                System.out.print(i);

                if (i != number - 1) {
                    System.out.print(", ");
                }
            }
        }

        System.out.print("\n");
    }

    public void printTriangles() {
        int triangleWidth = 5;
        int gap = 4;

        for (int i = 1; i < triangleWidth; i++) {
            System.out.print("*".repeat(i));
            System.out.print(" ".repeat(gap));
            System.out.println("*".repeat(triangleWidth - i));
        }

        System.out.println("\n");

        for (int i = triangleWidth - 1; i > 0; i--) {
            System.out.print("*".repeat(i));
            System.out.print(" ".repeat(gap));
            System.out.println("*".repeat(triangleWidth - i));
        }
    }
}
