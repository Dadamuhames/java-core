package lessons.lesson03.bankaccount;


public class BankAccountApplication {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("My Name", 345666, 69.99);

        System.out.printf("Balance: %s\n", bankAccount.getBalance());

        bankAccount.deposit(1000.0);

        System.out.printf("Balance: %s\n", bankAccount.getBalance());

        bankAccount.withdraw(70.00);

        System.out.printf("Balance: %s\n", bankAccount.getBalance());
    }
}
