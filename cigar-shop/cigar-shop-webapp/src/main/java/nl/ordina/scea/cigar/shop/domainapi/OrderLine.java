package nl.ordina.scea.cigar.shop.domainapi;

public interface OrderLine {
    Money getAmount();

    String getProductName();

    int getQuantity();

    Money getPrice();
}
