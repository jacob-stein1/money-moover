package seedu.duke.commands;

import seedu.duke.Account;
import seedu.duke.AccountList;
import seedu.duke.Forex;
import seedu.duke.Currency;
import seedu.duke.ui.Ui;
import seedu.duke.constants.ErrorMessage;

import seedu.duke.exceptions.NoAccountException;
// import seedu.duke.exceptions.InvalidExchangeArgumentException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.NotEnoughInAccountException;
import seedu.duke.exceptions.InvalidUpdateBalanceActionException;


public class ExchangeCommand extends Command {

    /**
     * Constructor for exchange command
     *
     * @param input input for exchange command
     */
    public ExchangeCommand (String input) {
        super(false, input);
    }

    /**
     * Converts the requested amount and changes the account balances
     */
    @Override
    public void execute (Ui ui, AccountList accounts) {
        try {
            // Parse input
            Forex exchangeRate = formatInput();
            float amount = parseAmount();

            // Retrieve and edit accounts
            Account oldAcc = accounts.getAccount(exchangeRate.getInitial());
            Account newAcc = accounts.getAccount(exchangeRate.getTarget());
            oldAcc.updateBalance(amount, "subtract");
            newAcc.updateBalance(exchangeRate.convert(amount), "add");
            ui.printMessage(exchangeRate);
            ui.printMessage("Balance of initial account --> " + oldAcc);
            ui.printMessage("Balance of target account --> " + newAcc);

            // Exception handling
        } catch (NoAccountException e) {
            ui.printMessage(ErrorMessage.NO_SUCH_ACCOUNT);
        } catch (IllegalArgumentException e) {
            ui.printMessage(ErrorMessage.INVALID_CURRENCY);
        } catch (AssertionError e) {
            ui.printMessage(ErrorMessage.INVALID_EXCHANGE_ARGUMENT);
        } catch (InvalidNumberException e) {
            ui.printMessage(ErrorMessage.INVALID_NUMBER);
        } catch (NotEnoughInAccountException e) {
            ui.printMessage(ErrorMessage.NOT_ENOUGH_IN_ACCOUNT);
        } catch (InvalidUpdateBalanceActionException e) {
            ui.printMessage(ErrorMessage.INVALID_UPDATE_BALANCE_ACTION);
        }
    }

    /**
     * Converts input into Forex object for use in execution
     *
     * @return Forex object with intial and target currencies
     * @throws IllegalArgumentException         if the currencies are not supported
     * @throws InvalidExchangeArgumentException if arguments are incorrect
     */
    public Forex formatInput () throws AssertionError {
        String[] splitInput = input.trim().split(" ");
        // if (splitInput.length != 4) {
        //     throw new InvalidExchangeArgumentException();
        // }
        assert splitInput.length == 4;
        Currency initial = Currency.valueOf(splitInput[1]);
        Currency target = Currency.valueOf(splitInput[2]);
        return new Forex(initial, target);
    }

    /**
     * Retrieves the amount to be converted from the input
     *
     * @return float representing amount to be converted
     * @throws NullPointerException  if the amount is null
     * @throws NumberFormatException if the amount is non-numeric
     */
    public float parseAmount () throws InvalidNumberException {
        try {
            String amount = input.trim().split(" ")[3];
            float amountAsFloat = Float.parseFloat(amount);
            if (amountAsFloat <= 0) {
                throw new InvalidNumberException();
            }
            return amountAsFloat;
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
    }
}
