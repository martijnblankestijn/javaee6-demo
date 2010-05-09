package nl.ordina.scea.cigar.shop.access.external;

import nl.ordina.scea.cigar.shop.accessapi.ASynchronous;
import nl.ordina.scea.cigar.shop.accessapi.InventoryGateway;

@ASynchronous
public class InventoryJmsGateway implements InventoryGateway {
    @Override
    public int getAvailability(int productId) {
        //todo implement
        return 0;
    }
}
