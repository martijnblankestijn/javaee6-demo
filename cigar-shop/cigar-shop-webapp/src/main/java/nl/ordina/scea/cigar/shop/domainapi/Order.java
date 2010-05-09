package nl.ordina.scea.cigar.shop.domainapi;

import java.util.List;

/**
 * An Order.
 */
public interface Order {
    /**
     * @return Identifier.
     */
    int getId();

    /**
     * Add a line to the order.
     *
     * @param product  the product
     * @param quantity the desired quantity
     */
    void addProduct(Product product, int quantity);

    /**
     * @return the amount.
     */
    Money getAmount();

    /**
     * @return the method of shipping.
     */
    ShippingMethod getShippingMethod();

    /**
     * @return The lines of the order.
     */
    List<? extends OrderLine> getOrderLines();

    /**
     * @return The customer that placed the order.
     */
    Customer getCustomer();
}
