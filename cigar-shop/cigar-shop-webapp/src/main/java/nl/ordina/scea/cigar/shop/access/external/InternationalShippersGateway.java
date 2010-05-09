package nl.ordina.scea.cigar.shop.access.external;

import nl.ordina.scea.cigar.shop.accessapi.ShippingMethodSelector;
import nl.ordina.scea.cigar.shop.accessapi.ShippingService;
import nl.ordina.scea.cigar.shop.domainapi.ShippingMethod;
import nl.ordina.scea.cigar.shop.domainapi.ShippingRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gateway for the WebService of the International Shipper organization.
 */
@ApplicationScoped
@ShippingMethodSelector(method = ShippingMethod.International)
public class InternationalShippersGateway implements ShippingService {
    @Inject
    private transient Logger logger;

    /**
     * {@inheritDoc}
     */
    @Override
    public void shipOrder(ShippingRequest request) {
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Sending shipping request " + request);
        }
    }

}
