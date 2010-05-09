package nl.ordina.scea.cigar.shop.domainapi;

public interface Shipping {
    ShippingMethod getMethod();

    Money getPrice();

    String getDescription();

    int getId();
}
