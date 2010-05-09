package nl.ordina.scea.cigar.shop.domainapi;

/**
 */
public interface ShoppingCartItem {
    Product getProduct();

    Money getCartItemAmount();

    int getQuantity();
}
