package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.AccountList;
import seedu.duke.Currency;
import seedu.duke.constants.ErrorMessage;
import seedu.duke.exceptions.NoAccountException;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class CreateAccountTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_correctInputProvided_shouldCreateAccount() {
        try {
            Ui ui = new Ui();
            AccountList accounts = new AccountList();
            Command command = new CreateAccountCommand("create-account EUR");
            command.execute(ui, accounts);
            assertDoesNotThrow(() -> {
                accounts.getAccount(Currency.EUR);
            });
            assertThrows(NoAccountException.class, () -> {
                accounts.getAccount(Currency.USD);
            });
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void execute_multipleCurrenciesProvided_shouldThrowException() {
        Ui ui = new Ui();
        AccountList accounts = new AccountList();
        Command command = new CreateAccountCommand("create-account EUR USD");
        command.execute(ui, accounts);
        assertEquals(ErrorMessage.INVALID_CREATE_ACCOUNT_COMMAND, outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_invalidCurrencyProvided_shouldThrowException() {
        Ui ui = new Ui();
        AccountList accounts = new AccountList();
        Command command = new CreateAccountCommand("create-account XYZ");
        command.execute(ui, accounts);
        assertEquals(ErrorMessage.INVALID_CURRENCY, outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_currencyAccountAlreadyExists_shouldThrowException() {
        Ui ui = new Ui();
        AccountList accounts = new AccountList();
        Command command = new CreateAccountCommand("create-account EUR");
        command.execute(ui, accounts);
        command.execute(ui, accounts);
        assertEquals("You have successfully added the EUR account\n" +
                ErrorMessage.ACCOUNT_ALREADY_EXISTS, outputStreamCaptor.toString().trim());
    }
}
