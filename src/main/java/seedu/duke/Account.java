package seedu.duke;

import seedu.duke.exceptions.InvalidUpdateBalanceActionException;
import seedu.duke.exceptions.NotEnoughInAccountException;
import seedu.duke.exceptions.TooLargeAmountException;

import java.math.BigDecimal;

public class Account {
    private long balance;
    private final Currency currency;

    Account (float initialBalance, Currency currency) {
        this.currency = currency;
        balance = (long) (initialBalance * 100);
    }

    public float getBalance () {
        return balance / 100.0f;
    }

    public Currency getCurrencyType () {
        return currency;
    }

    /**
     * Updates the balance of the account
     *
     * @param changeInBalance the amount added or subtract from the account
     * @param action          specifies whether to add or subtract value
     * @throws NotEnoughInAccountException if the balance would become negative
     */
    public void updateBalance (BigDecimal changeInBalance, String action) throws NotEnoughInAccountException,
            InvalidUpdateBalanceActionException, TooLargeAmountException {
        long newBalance;
        if (action.equals("add")) {
            newBalance = balance + changeInBalance.multiply(BigDecimal.valueOf(100)).longValue();
            long UPPER_BOUND = 1_000_000_000;
            if (newBalance > UPPER_BOUND) {
                throw new TooLargeAmountException();
            }
        } else if (action.equals("subtract")) {
            newBalance = balance - changeInBalance.multiply(BigDecimal.valueOf(100)).longValue();
        } else {
            throw new InvalidUpdateBalanceActionException();
        }
        if (newBalance < 0) {
            throw new NotEnoughInAccountException();
        }
        balance = newBalance;
    }

    public static String currencyToString (Currency currency) {
        return currency.name();
    }

    @Override
    public String toString () {
        String currencyType = currencyToString(currency);
        return currencyType + ": " + String.format("%.2f", balance / 100.0);
    }

}
