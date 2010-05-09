package nl.ordina.scea.cigar.shop.access.external;

import nl.ordina.scea.cigar.shop.accessapi.CreditCardProcessor;
import nl.ordina.scea.cigar.shop.domainapi.CreditCard;
import nl.ordina.scea.cigar.shop.domainapi.Money;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gateway for credit card processing done by Merchant Bank
 */
@ApplicationScoped
public class CreditCardGateway implements CreditCardProcessor {
    @Inject
    private transient Logger logger;

    /**
     * {@inheritDoc}
     */
    @Override
    public void authorizePayment(CreditCard creditCard, Money amount, String description) {
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Authorize payment for credit card" + creditCard + " and amount " + amount + " with description " + description);
        }
    }
}
