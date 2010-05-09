package nl.ordina.scea.cigar.shop.domainapi;

/**
 * A credit card.
 */
public interface CreditCard {
    /**
     * @return The number.
     */
    Long getNumber();

    /**
     * @return The type of credit card.
     */
    CreditCardType getType();

    /**
     * @return The month of expiration .
     */
    Integer getExpirationMonth();

    /**
     * @return The year of expiration.
     */
    Integer getExpirationYear();
}
