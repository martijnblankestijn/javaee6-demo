package nl.ordina.scea.cigar.shop.web.factory;

import nl.ordina.scea.cigar.shop.accessapi.ShippingRepository;
import nl.ordina.scea.cigar.shop.domainapi.Shipping;

import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class ShippingMethodSelectItemFactory {
    @Inject
    private ShippingRepository shippingRepository;

    @Produces
    @Named
    public SelectItem[] getShippingMethods() {
        final List<Shipping> shippingAlternatives = getShippingAlternatives();

        SelectItem[] items = new SelectItem[shippingAlternatives.size()];
        for (int i = 0; i < items.length; i++) {

            final Shipping alternative = shippingAlternatives.get(i);
            items[i] = new SelectItem(alternative, alternative.getDescription() + " " + alternative.getPrice());

        }
        return items;

    }

    private List<Shipping> getShippingAlternatives() {
        return shippingRepository.getShippingAlternatives();
    }
}
