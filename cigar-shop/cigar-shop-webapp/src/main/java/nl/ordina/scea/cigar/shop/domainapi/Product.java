package nl.ordina.scea.cigar.shop.domainapi;

/**
 */
public interface Product {
    ProductCategory getCategory();

    String getName();

    String getDescription();

    Money getPrice();

    int getId();
}
