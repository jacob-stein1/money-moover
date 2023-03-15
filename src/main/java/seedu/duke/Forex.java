package seedu.duke;

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
    private static HashMap<Currency, Float> exchangeRates = new HashMap<Currency, Float>();

    private Currency initial;
    private Currency target;

    // Constructor
    public Forex(Currency from, Currency to) {
        populateRates();
        initial = from;
        target = to;
    }

    // Sets dummy equal exchange rate to SGD
    public static void populateRates() {
        for (Currency currency : Currency.values()) {
            if (Account.currencyToString(currency).equals("SGD")) {
                exchangeRates.put(currency, 1.0f);
            } else {
                exchangeRates.put(currency, 5.0f);
            }
        }
    }

    // Prints the exchange relationship
    @Override
    public String toString() {
        return "Exchanging from " + initial + " to " + target;
    }

    /**
     * Converts an amount from the initial currency to the target currency.
     * First converts initial into SGD, then from SGD into target.
     * @param amount amount to be converted from initial to target
     * @return amount converted to target currency
     */
    public float convert(float amount) {
        float amountInSGD = amount / exchangeRates.get(initial);
        float amountInTarget = amountInSGD * exchangeRates.get(target);
        return amountInTarget;
    }

    // Accessor methods
    public Currency getInitial() {
        return initial;
    }
    public Currency getTarget() {
        return target;
    }
    public static float getExchangeRate(Currency currency) {
        return exchangeRates.get(currency);
    }
}
