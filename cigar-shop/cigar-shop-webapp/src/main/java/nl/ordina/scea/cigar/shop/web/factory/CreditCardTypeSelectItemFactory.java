package nl.ordina.scea.cigar.shop.web.factory;

import nl.ordina.scea.cigar.shop.domainapi.CreditCardType;

import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreditCardTypeSelectItemFactory {
    @Produces
    @Named
    public List<SelectItem> getCreditCardTypes() {
        final CreditCardType[] types = CreditCardType.values();

        List<SelectItem> typeItems = new ArrayList<SelectItem>(types.length);
        for (CreditCardType type : types) {
            typeItems.add(new SelectItem(type));
        }
        return Collections.unmodifiableList(typeItems);

    }
}
