package nl.ordina.scea.cigar.shop.accessapi;

import nl.ordina.scea.cigar.shop.domainapi.Shipping;

import java.util.List;

public interface ShippingRepository {
    List<Shipping> getShippingAlternatives();
}
