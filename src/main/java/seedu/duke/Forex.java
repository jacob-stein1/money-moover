package seedu.duke;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * Forex object represents the relationship between two currencies. All
 * exchange rates are stored in a hash map that uses the ticker as a key
 * and maps to the value of S$1 in that currency.
 */
public class Forex {

    /**
     * Hash map to store all exchange rates. Rather than a 2D array,
     * just stores rate of S$1 in each currency. All exchanges
     * convert first to SGD, then from SGD to their target. To be
     * implemented using API.
     */
    private static final HashMap<Currency, BigDecimal> exchangeRates = new HashMap<Currency, BigDecimal>();

    private Currency initial;
    private Currency target;

    // Constructor
    public Forex (Currency from, Currency to) {
        populateRates();
        initial = from;
        target = to;
    }

    /**
     * Populates the exchange rates hash map with the current exchange rates,
     * correct as of 2023-03-15 18:15 SGT according to
     * <a href="https://www.xe.com/currencyconverter/convert">https://www.xe.com/currencyconverter/convert</a>
     */
    public static void populateRates () {
        exchangeRates.put(Currency.SGD, BigDecimal.valueOf(1.000f));
        exchangeRates.put(Currency.MYR, BigDecimal.valueOf(3.3295727f));
        exchangeRates.put(Currency.USD, BigDecimal.valueOf(0.74265183f));
        exchangeRates.put(Currency.IDR, BigDecimal.valueOf(11442.776f));
        exchangeRates.put(Currency.JPY, BigDecimal.valueOf(99.414735f));
        exchangeRates.put(Currency.THB, BigDecimal.valueOf(25.689173f));
        exchangeRates.put(Currency.CNY, BigDecimal.valueOf(5.1222378f));
        exchangeRates.put(Currency.GBP, BigDecimal.valueOf(0.61293137f));
        exchangeRates.put(Currency.EUR, BigDecimal.valueOf(0.69567172f));
        exchangeRates.put(Currency.KRW, BigDecimal.valueOf(974.90478f));
        exchangeRates.put(Currency.VND, BigDecimal.valueOf(17490.625f));
    }

    // Prints the exchange relationship
    @Override
    public String toString () {
        return "Exchanging from " + initial + " to " + target;
    }

    /**
     * Converts an amount from the initial currency to the target currency.
     * First converts initial into SGD, then from SGD into target.
     *
     * @param amount amount to be converted from initial to target
     * @return amount converted to target currency
     */
    public BigDecimal convert (BigDecimal amount) {
        BigDecimal amountInSGD = amount.divide(
                exchangeRates.get(initial), 10, RoundingMode.HALF_UP
        );
        BigDecimal amountInTarget = amountInSGD.multiply(exchangeRates.get(target));
        return amountInTarget;
    }

    // Accessor methods
    public Currency getInitial () {
        return initial;
    }

    public Currency getTarget () {
        return target;
    }
    public static float getExchangeRate(Currency currency) {
        return exchangeRates.get(currency);
    }
}
