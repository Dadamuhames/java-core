package lessons.lesson03.banksystem;

public class BankAccount {
    private final String fio;
    private final Integer accountNumber;
    private Double balance;

    public BankAccount(String fio, Integer accountNumber, Double balance) {
        this.fio = fio;
        this.accountNumber = accountNumber;
        this.balance = balance;
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

    public void setBalance(final Double amount) {
        this.balance = amount;
    }


    @Override
    public String toString() {
        return String.format("Fio: %s\nAccount number: %s\nBalance: %s", fio, accountNumber, balance);
    }
}
