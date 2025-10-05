package lessons.lesson03.banksystem;

import java.util.Scanner;

public class BankSystem {
    private final BankSystemService bankSystemService;
    private final Scanner scanner;

    public BankSystem(BankSystemService bankSystemService, Scanner scanner) {
        this.bankSystemService = bankSystemService;
        this.scanner = scanner;
    }

    public void getAccountInfo() {
        System.out.print("Enter account number:");

        Integer accountNumber = scanner.nextInt();

        scanner.nextLine();

        try {
            System.out.println("Bank Account Info");
            BankAccount bankAccount = bankSystemService.getBankAccount(accountNumber);
            System.out.println(bankAccount.toString());
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }

    public void addAccount() {
        System.out.println("Adding new account");

        System.out.print("Enter fio:");
        String fio = scanner.nextLine();

        BankAccount bankAccount = bankSystemService.createBankAccount(fio);

        System.out.printf("Account created with number: %d\n", bankAccount.getAccountNumber());
    }

    public void replenishAccount() {
        System.out.println("Replenish account");

        System.out.print("Enter account number: ");
        Integer accountNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            bankSystemService.replenishAccount(accountNumber, amount);
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }

    public void deleteAccount() {
        System.out.println("Delete account");

        System.out.print("Enter account number:");

        Integer accountNumber = scanner.nextInt();

        try {
            bankSystemService.deleteAccount(accountNumber);
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }

    public void transferMoneyBetweenAccounts() {
        System.out.println("Transfer money");

        System.out.print("Enter account number to transfer money from:");
        Integer fromAccountNumber = scanner.nextInt();

        System.out.print("Enter account number to transfer money to:");
        Integer toAccountNumber = scanner.nextInt();

        System.out.print("Enter the amount to transfer:");
        Double amount = scanner.nextDouble();

        try {
            bankSystemService.transferMoneyBetweenAccounts(fromAccountNumber, toAccountNumber, amount);
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }
}
