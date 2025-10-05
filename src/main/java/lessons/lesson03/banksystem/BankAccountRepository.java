package lessons.lesson03.banksystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BankAccountRepository {
    private final Map<Integer, BankAccount> acounts;

    public BankAccountRepository() {
        this.acounts = new HashMap<>();
    }


    public Optional<BankAccount> findByAccountNumber(final Integer accountNumber) {
        return Optional.ofNullable(acounts.get(accountNumber));
    }

    public boolean existsByAccountNumber(final Integer accountNumber) {
        return acounts.containsKey(accountNumber);
    }


    public void save(final BankAccount bankAccount) {
        Integer accountNumber = bankAccount.getAccountNumber();

        acounts.put(accountNumber, bankAccount);
    }


    public void updateBalance(final Integer accountNumber, final Double balance) {
        acounts.get(accountNumber).setBalance(balance);
    }


    public void delete(final Integer accountNumber) {
        acounts.remove(accountNumber);
    }
}
