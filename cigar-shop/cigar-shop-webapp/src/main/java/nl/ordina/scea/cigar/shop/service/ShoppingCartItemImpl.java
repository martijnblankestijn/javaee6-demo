package nl.ordina.scea.cigar.shop.service;

import nl.ordina.scea.cigar.shop.domainapi.Money;
import nl.ordina.scea.cigar.shop.domainapi.Product;
import nl.ordina.scea.cigar.shop.domainapi.ShoppingCartItem;

public class ShoppingCartItemImpl implements ShoppingCartItem {
    private final Product product;
    private int quantity;

    public ShoppingCartItemImpl(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public Money getCartItemAmount() {
        return product.getPrice().times(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
