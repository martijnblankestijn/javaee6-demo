package nl.ordina.scea.cigar.shop.web.factory;

import nl.ordina.scea.cigar.shop.domainapi.ProductCategory;
import nl.ordina.scea.cigar.shop.web.MessageResolver;

import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Locale;

public class ProductCategoryFactory {
    @Inject
    private transient MessageResolver i18n;

    @Produces
    @Named("productCategories")
    public List<SelectItem> getCategory2List(Locale locale) {
        return i18n.getItemsFor(locale, ProductCategory.values());
    }

}
