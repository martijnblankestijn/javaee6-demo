package nl.ordina.scea.cigar.shop.web.beans;

import nl.ordina.scea.cigar.shop.domainapi.Money;
import nl.ordina.scea.cigar.shop.domainapi.Product;
import nl.ordina.scea.cigar.shop.domainapi.ProductCategory;

/**
 * Form Bean consisting of the details of a product.
 */
public class ProductDetailsForm {
    private final Product product;
    private final boolean available;

    public ProductDetailsForm(Product product, boolean available) {
        this.product = product;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public ProductCategory getCategory() {
        return product.getCategory();
    }

    public String getName() {
        return product.getName();
    }

    public String getDescription() {
        return product.getDescription();
    }

    public Product getProduct() {
        return product;
    }

    public Money getPrice() {
        return product.getPrice();
    }

}
