package nl.ordina.scea.cigar.shop.accessapi;

import nl.ordina.scea.cigar.shop.domainapi.ShippingRequest;

/**
 * Service for shipping product.
 */
public interface ShippingService {
    /**
     * @param request the request for shipping products.
     */
    void shipOrder(ShippingRequest request);
}
