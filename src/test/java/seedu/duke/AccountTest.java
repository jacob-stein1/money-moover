package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.TooLargeAmountException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AccountTest {
    @Test
    public void updateBalance_exceedAmount_shouldThrowException () {
        Account account = new Account(0, Currency.JPY);
        BigDecimal tenMilOne = BigDecimal.valueOf(10_000_001);
        assertThrows(TooLargeAmountException.class,
                () -> account.updateBalance(tenMilOne, "add")
        );
    }

    @Test
    public void updateBalance_validInput_shouldAddCorrectly () {
        Account account = new Account(0, Currency.JPY);
        BigDecimal tenMil = BigDecimal.valueOf(10_000_000);
        assertDoesNotThrow(() -> account.updateBalance(tenMil, "add"));
        assertEquals(10_000_000, account.getBalance());
    }
}
