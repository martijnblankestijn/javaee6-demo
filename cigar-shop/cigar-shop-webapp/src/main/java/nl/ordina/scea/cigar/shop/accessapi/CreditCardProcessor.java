package nl.ordina.scea.cigar.shop.accessapi;

import nl.ordina.scea.cigar.shop.domainapi.CreditCard;
import nl.ordina.scea.cigar.shop.domainapi.Money;

/**
 * Processor for CreditCard payments.
 */
public interface CreditCardProcessor {
    /**
     * Authorize the CreditCard payment
     *
     * @param creditCard  the CreditCard to authorize
     * @param amount      the amount
     * @param description the description for the customer to identify the payment
     */
    void authorizePayment(CreditCard creditCard, Money amount, String description);
}
