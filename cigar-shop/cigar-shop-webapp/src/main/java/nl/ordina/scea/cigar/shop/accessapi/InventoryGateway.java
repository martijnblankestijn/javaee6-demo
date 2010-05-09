package nl.ordina.scea.cigar.shop.accessapi;

/**
 * Gateway for inventory services
 */
public interface InventoryGateway {
    /**
     * Retrieve the availability of a product
     *
     * @param productId the id of the product
     * @return the number of available products
     */
    int getAvailability(int productId);
}
