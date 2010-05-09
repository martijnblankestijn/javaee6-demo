package nl.ordina.scea.cigar.shop.accessapi;

import nl.ordina.scea.cigar.shop.domainapi.Customer;
import nl.ordina.scea.cigar.shop.domainapi.Order;
import nl.ordina.scea.cigar.shop.domainapi.Shipping;

/**
 * Repository for orders.
 */
public interface OrderRepository {
    /**
     * Fetches an order with the specified order number.
     *
     * @param orderNumber the number of the order
     * @return the found order
     */
    Order fetchById(Integer orderNumber);

    /**
     * Persist the order
     *
     * @param order the order
     */
    void persist(Order order);

    /**
     * Create a new order with the specified shipping.
     *
     * @param customer the customer
     * @param shipping the shipping method  @return the new (not yet persisted!!!) order.
     * @return the created order
     */
    Order createOrder(Customer customer, Shipping shipping);
}
