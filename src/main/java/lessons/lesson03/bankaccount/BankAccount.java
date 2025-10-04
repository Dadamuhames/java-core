package lessons.lesson03.bankaccount;


public class BankAccount {
    private final String fio;
    private final Integer accountNumber;
    private Double balance;


    public BankAccount(String fio, Integer accountNumber, Double balance) {
        this.fio = fio;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Внесено: " + amount);
        } else {
            System.out.println("Сумма должна быть больше 0");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Снято: " + amount);
        } else if (amount <= 0) {
            System.out.println("Сумма должна быть больше 0");
        } else {
            System.out.println("Недостаточно средств на счете");
        }
    }

    public Double getBalance() {
        return this.balance;
    }


    public String getFio() {
        return fio;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }
}
