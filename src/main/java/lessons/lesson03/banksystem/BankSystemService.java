package lessons.lesson03.banksystem;

import java.util.Random;

public class BankSystemService {
    private final BankAccountRepository bankAccountRepository;
    private final Random random;


    public BankSystemService(BankAccountRepository bankAccountRepository, Random random) {
        this.bankAccountRepository = bankAccountRepository;
        this.random = random;
    }


    public BankAccount getBankAccount(final Integer accountNumber) throws Exception {
        return bankAccountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new Exception("Bank account not found"));
    }

    private Integer generateAccountNumber() {
        int MIN = 100000;
        int MAX = 999999;

        int randomNumber = random.nextInt((MAX - MIN) + 1) + MIN;

        if (bankAccountRepository.existsByAccountNumber(randomNumber)) {
            return generateAccountNumber();
        }

        return randomNumber;
    }


    public BankAccount createBankAccount(final String fio) {
        Integer accountNumber = generateAccountNumber();

        BankAccount bankAccount = new BankAccount(fio, accountNumber, 0.0);

        bankAccountRepository.save(bankAccount);

        return bankAccount;
    }


    public void replenishAccount(final Integer accountNumber, final Double amount) throws Exception {
        BankAccount bankAccount = getBankAccount(accountNumber);

        if (amount <= 0) {
            throw new Exception("Amount must be greater then 0");
        }

        Double newBalance = bankAccount.getBalance() + amount;

        bankAccountRepository.updateBalance(accountNumber, newBalance);
    }

    public void deleteAccount(final Integer accountNumber) throws Exception {
        boolean accountExists = bankAccountRepository.existsByAccountNumber(accountNumber);

        if (!accountExists) {
            throw new Exception("Account doesn't exist");
        }

        bankAccountRepository.delete(accountNumber);

        System.out.println("Account deleted");
    }

    public void transferMoneyBetweenAccounts(final Integer fromAccountNumber, final Integer toAccountNumber, final Double amount) throws Exception {
        BankAccount fromAccount = getBankAccount(fromAccountNumber);
        BankAccount toAccount = getBankAccount(toAccountNumber);

        if (amount < 0 || fromAccount.getBalance() < amount) {
            throw new Exception("Amount invalid");
        }

        Double fromAccountNewBalance = fromAccount.getBalance() - amount;
        Double toAccountNewBalance = toAccount.getBalance() + amount;

        bankAccountRepository.updateBalance(fromAccountNumber, fromAccountNewBalance);
        bankAccountRepository.updateBalance(toAccountNumber, toAccountNewBalance);
    }
}
