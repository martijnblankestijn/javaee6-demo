package nl.ordina.scea.cigar.shop.domainapi;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Represent an amount of money in any currency.
 */
@Embeddable
@Access(value = AccessType.FIELD)
public class Money implements Comparable<Money>, Serializable {
    public static final Money ZERO = new Money(BigDecimal.ZERO);
    private static final String DEFAULT_CURRENCY = "EUR";

    private BigDecimal amount;
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    /**
     * Only for persistence provider!.
     */
    protected Money() {
    }

    private Money(BigDecimal amount) {
        this(amount, DEFAULT_CURRENCY);
    }

    private Money(BigDecimal amount, String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    @Override
    public int compareTo(Money other) {
        assertSameCurrency(other);
        return this.amount.compareTo(other.amount);
    }

    public static Money fromCents(int cents) {
        BigDecimal db = new BigDecimal(cents);
        return new Money(db.divide(new BigDecimal(100)));
    }

    public static Money fromCents(int cents, Currency currency) {
        BigDecimal db = new BigDecimal(cents);
        return new Money(db.divide(new BigDecimal(100)), currency.getCurrencyCode());
    }

    public long getWhole() {
        return amount.longValue();
    }

    public Money times(int times) {
        return new Money(amount.multiply(new BigDecimal(times)), currencyCode);
    }

    public Money add(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.add(other.amount), currencyCode);
    }

    private void assertSameCurrency(Money other) {
        if (!this.currencyCode.equals(other.currencyCode)) {
            throw new IncompatibleCurrencyException(this.currencyCode, other.currencyCode);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!amount.equals(money.amount)) return false;
        return currencyCode.equals(money.currencyCode);

    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + currencyCode.hashCode();
        return result;
    }

    public static class IncompatibleCurrencyException extends RuntimeException {
        public IncompatibleCurrencyException(String currencyCode, String otherCurrencyCode) {
            super("Expected currency " + currencyCode + " does not match given currency " + otherCurrencyCode);
        }
    }

    @Override
    public String toString() {
        return currencyCode + " " + amount;
    }
}
