package nl.ordina.scea.cigar.shop.domainapi;

import java.util.List;

/**
 * Shopping Cart of the Customer
 */
public interface ShoppingCart {
    /**
     * Adds a product to the shopping cart if it is not already in the shopping cart
     *
     * @param product the product
     */
    void addProduct(Product product);

    /**
     * Change the quantity of the product
     *
     * @param product     the product
     * @param newQuantity the new quantity
     */
    void changeQuantity(Product product, int newQuantity);

    /**
     * Retrieve the items of the shopping cart.
     * This is a snapshot of the items!
     *
     * @return the items
     */
    List<ShoppingCartItem> getItems();

    /**
     * @return the total amount of the cart items.
     */
    Money getTotalAmount();

    /**
     * Clear the shopping cart.
     */
    void clear();


    /**
     * Remove the product from the shopping Cart.
     *
     * @param product the product
     */
    void remove(Product product);
}

