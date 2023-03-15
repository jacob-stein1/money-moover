package seedu.duke.commands;

import seedu.duke.AccountList;
import seedu.duke.Currency;
import seedu.duke.ui.Ui;
import seedu.duke.Forex;
import seedu.duke.Account;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.InvalidShowrateArgumentException;
import seedu.duke.constants.ErrorMessage;

import java.math.BigDecimal;

/**
 * Command to print the exchange rate between two currencies
 */
public class ShowRateCommand extends Command {
    private Currency from;
    private Currency to;

    public ShowRateCommand (String input) {
        super(false, input);
    }

    /**
     * Prints the exchange rate between two currencies.
     * If no value is given, shows 1:X exchange rates, otherwise
     * prints the value exchanged to the target currency.
     */
    @Override
    public void execute (Ui ui, AccountList accounts) {
        String[] args = input.split(" ");
        try {
            BigDecimal val;
            if (args.length < 3 || args.length > 4) {
                throw new InvalidShowrateArgumentException();
            }
            Currency from = Currency.valueOf(args[1]);
            Currency to = Currency.valueOf(args[2]);
            if (args.length == 4) {
                val = new BigDecimal(args[3]);
            } else {
                val = BigDecimal.valueOf(1);
            }
            Forex reverse = new Forex(to, from);
            Forex instance = new Forex(from, to);
            ui.printMessage(getRateString(instance, val));
            ui.printMessage(getRateString(reverse, val));

        } catch (IllegalArgumentException e) {
            if (args.length == 4 && !args[3].matches("[0-9\\.]+")) {
                ui.printMessage(ErrorMessage.INVALID_NUMBER);
            } else {
                ui.printMessage(ErrorMessage.INVALID_CURRENCY);
            }
        } catch (InvalidNumberException e) {
            ui.printMessage(ErrorMessage.NEGATIVE_NUMBER);
        } catch (InvalidShowrateArgumentException e) {
            ui.printMessage(ErrorMessage.SHOWRATE_SYNTAX);
        }
    }

    /**
     * Prints the exchange rate between two currencies with a specified amount
     *
     * @param instance a Forex object containing the exchange rate
     * @param amt      a float of the amount to be converted on the exchange rate
     * @return a string containing the exchange rates to be printed
     * @throws InvalidNumberException if the amount is negative
     */
    private String getRateString (Forex instance, BigDecimal amt) throws InvalidNumberException {
        if (amt.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidNumberException();
        }
        String from = Account.currencyToString(instance.getInitial());
        String to = Account.currencyToString(instance.getTarget());
        return String.format("%.2f %s = %,10.6f %s", amt, from, instance.convert(amt), to);
    }
}
