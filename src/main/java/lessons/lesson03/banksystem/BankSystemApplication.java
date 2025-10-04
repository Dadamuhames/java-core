package lessons.lesson03.banksystem;

import java.util.Random;
import java.util.Scanner;

public class BankSystemApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            BankAccountRepository bankAccountRepository = new BankAccountRepository();
            BankSystemService bankSystemService = new BankSystemService(bankAccountRepository, random);
            BankSystem bankSystem = new BankSystem(bankSystemService, scanner);


            printOptions();
            String option = scanner.nextLine();

            while (true) {
                switch (option) {
                    case "I" -> bankSystem.getAccountInfo();

                    case "A" -> bankSystem.addAccount();

                    case "R" -> bankSystem.replenishAccount();

                    case "T" -> bankSystem.transferMoneyBetweenAccounts();

                    case "D" -> bankSystem.deleteAccount();

                    case "Q" -> {
                        clearTerminal();
                        System.exit(0);
                    }
                }

                printOptions();
                option = scanner.nextLine();
                clearTerminal();
            }
        }
    }

    public static void printOptions() {
        System.out.println(
            "Choose section:\n[I] - get account info\n[A] - Add account\n[R] - Replenish account\n[D] - Delete account\n[T] - Transfer money\n[Q] - Quit"
        );
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
