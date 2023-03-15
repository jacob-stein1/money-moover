package seedu.duke.constants;

/**
 * A public interface that is used to contain all the error messages throughout the application.
 */
public interface ErrorMessage {
    String INVALID_ADD_COMMAND = "Please check that you have correctly provided the currency and amount";
    String INVALID_AMOUNT_TO_ADD = "Please provide a value greater than 0";
    String INVALID_CURRENCY = "An invalid currency has been provided.";
    String INVALID_NUMERICAL_AMOUNT = "Please provide a float amount to update";
    String INVALID_CREATE_ACCOUNT_COMMAND = "create-account must be used with the following format: " +
            "create-account <CURRENCY>";
    String MORE_THAN_ONE_CURRENCY_PROVIDED = "Please do not provide more than one currency.";
    String NO_SUCH_ACCOUNT = "You do not have an account for the currency.";

    String INVALID_WITHDRAW_COMMAND = "Please check that you have correctly provided the currency and amount";
    String INSUFFICIENT_WITHDRAW_BALANCE = "You do not have sufficient balance to withdraw.";
    String NO_AMOUNT_PROVIDED = "Please provide an amount to be added.";

    String INVALID_EXCHANGE_ARGUMENT = "Please structure your exchange as " +
            "'exchange STARTING_CURRENCY TARGET_CURRENCY AMOUNT_IN_STARTING'";
    String NOT_ENOUGH_IN_ACCOUNT = "Please ensure you have enough money in your" +
            " starting currency account to perform this transaction";
    String INVALID_NUMBER = "Please enter a valid number to exchange";
    String INVALID_DELETE_ACCOUNT_COMMAND = "Please check that you have correctly provided the " +
            "currency account to delete";
    String ACCOUNT_NOT_EMPTY = "Please empty your currency account before account deletion";
    String NEGATIVE_NUMBER = "Please enter a positive number to show the rate!";
    String SHOWRATE_SYNTAX = "Please structure show-rate as 'show-rate CURRENCY CURRENCY [AMOUNT]'";
    String ACCOUNT_ALREADY_EXISTS = "You already have an account of this currency";
    String INVALID_UPDATE_BALANCE_ACTION = "The provided action for updating balance was invalid";
    String EXCHANGE_AMOUNT_TOO_SMALL = "The amount is too small to exchange " +
            "(e.g. does not become at least 0.01 in the target currency)";

}
