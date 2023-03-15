package seedu.duke.commands;

import seedu.duke.Account;
import seedu.duke.AccountList;
import seedu.duke.Currency;
import seedu.duke.constants.ErrorMessage;
import seedu.duke.constants.Message;
import seedu.duke.exceptions.InvalidWithdrawCommandException;
import seedu.duke.exceptions.NoAccountException;
import seedu.duke.exceptions.NotEnoughInAccountException;
import seedu.duke.exceptions.InvalidUpdateBalanceActionException;
import seedu.duke.exceptions.TooLargeAmountException;
import seedu.duke.ui.Ui;

import java.math.BigDecimal;

/**
 * This class is used to deal with the withdrawCommand.
 */
public class WithdrawCommand extends Command {
    private Currency currency;
    private BigDecimal amount;

    /**
     * @param input The user input including the command.
     */
    public WithdrawCommand (String input) {
        super(false, input);
    }

    private Currency getCurrency (String currencyString) {
        return Currency.valueOf(currencyString);
    }

    private void processCommand () throws InvalidWithdrawCommandException {
        String[] words = super.input.split(" ");
        // Format: [Command, CURRENCY, AMOUNT]
        boolean isValidCommand = words.length == 3;
        if (!isValidCommand) {
            throw new InvalidWithdrawCommandException();
        }
        this.currency = getCurrency(words[1]);
        this.amount = new BigDecimal(words[2]);
        if (this.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidWithdrawCommandException();
        }


    }

    private void printSuccess (Ui ui, float newBalance) {
        ui.printf(Message.SUCCESSFUL_WITHDRAW_COMMAND.getMessage(), this.amount, this.currency.name(),
                newBalance, this.currency.name());
        ui.printNewLine();
    }

    /**
     * Withdraw the currency into the existing account if found and print a success message.
     *
     * @param ui The instance of the UI class.
     */
    @Override
    public void execute (Ui ui, AccountList accounts) {
        try {
            processCommand();
            Account account = accounts.getAccount(this.currency);
            account.updateBalance(this.amount, "subtract");
            printSuccess(ui, account.getBalance());
        } catch (InvalidWithdrawCommandException e) {
            ui.printMessage(ErrorMessage.INVALID_WITHDRAW_COMMAND);
        } catch (NumberFormatException e) {
            ui.printMessage(ErrorMessage.INVALID_NUMERICAL_AMOUNT);
        } catch (IllegalArgumentException e) {
            ui.printMessage(ErrorMessage.INVALID_CURRENCY);
        } catch (NoAccountException e) {
            ui.printMessage(ErrorMessage.NO_SUCH_ACCOUNT);
        } catch (NotEnoughInAccountException e) {
            ui.printMessage(ErrorMessage.NOT_ENOUGH_IN_ACCOUNT);
        } catch (InvalidUpdateBalanceActionException e) {
            ui.printMessage(ErrorMessage.INVALID_UPDATE_BALANCE_ACTION);
        } catch (TooLargeAmountException e) {
            ui.printMessage(ErrorMessage.EXCEED_AMOUNT_ALLOWED);
        }
    }
}
