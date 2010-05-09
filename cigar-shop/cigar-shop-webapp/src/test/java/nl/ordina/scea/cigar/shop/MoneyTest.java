package nl.ordina.scea.cigar.shop;

import nl.ordina.scea.cigar.shop.domainapi.Money;
import org.junit.Test;

import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MoneyTest {
    @Test
    public void testWholeFromCents() {
        assertEquals(1, Money.fromCents(100).getWhole());
        assertEquals(0, Money.fromCents(99).getWhole());
        assertEquals(1, Money.fromCents(101).getWhole());
        assertEquals(5, Money.fromCents(589).getWhole());
    }

    @Test
    public void testTimes() {
        assertEquals(Money.fromCents(500), Money.fromCents(100).times(5));
    }


    @Test
    public void testAddition() {
        assertEquals(Money.fromCents(450), Money.fromCents(100).add(Money.fromCents(350)));
    }

    @Test(expected = Money.IncompatibleCurrencyException.class)
    public void testAdditionWithDifferentCurrency() {
        fromCents(100, "EUR").add(fromCents(350, "USD"));
    }

    @Test
    public void compareTo() {
        final Money money = fromCents(500, "EUR");
        // compare to self
        assertEquals(0, money.compareTo(money));
        assertEquals(0, money.compareTo(fromCents(500, "EUR")));
        assertTrue(money.compareTo(fromCents(499, "EUR")) > 0);
        assertTrue(money.compareTo(fromCents(501, "EUR")) < 0);

    }


    private Money fromCents(int cents, String isoCode) {
        return Money.fromCents(cents, Currency.getInstance(isoCode));
    }

}
