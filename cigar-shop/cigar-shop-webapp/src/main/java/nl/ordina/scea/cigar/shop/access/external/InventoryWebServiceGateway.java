package nl.ordina.scea.cigar.shop.access.external;

import nl.ordina.cursus.inventory.domain.v1.AvailabilityRequest;
import nl.ordina.cursus.inventory.domain.v1.AvailabilityResponse;
import nl.ordina.cursus.inventory.service.v1.InventoryService;
import nl.ordina.scea.cigar.shop.accessapi.InventoryGateway;
import nl.ordina.scea.cigar.shop.accessapi.Synchronous;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gateway to the back end web service
 */
@Synchronous
public class InventoryWebServiceGateway implements Serializable, InventoryGateway {
    private final transient Logger logger;
    private final InventoryService inventoryService;

    /**
     * @param inventoryService the inventory service
     * @param logger           the logger to use
     */
    @Inject
    public InventoryWebServiceGateway(final InventoryService inventoryService, final Logger logger) {
        this.inventoryService = inventoryService;
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public int getAvailability(int productId) {
        AvailabilityRequest request = new AvailabilityRequest();
        request.setProductId(productId);


        AvailabilityResponse response = inventoryService.getAvailability(request);

        if (logger.isLoggable(Level.FINE)) {
            logger.fine(" Client  available " + response.getAvailable() + " for product " + request.getProductId());
        }
        return response.getAvailable();
    }
}
