package nl.ordina.scea.cigar.shop.domainapi;

/**
 * Service for processing orders.
 */
public interface OrderService {
    /**
     * Place an order
     *
     * @param customer   the customer of the order
     * @param order      the order
     * @param creditCard the CreditCard to pay for the order.
     * @return the unique number of the order
     */
    public int placeOrder(Customer customer, Order order, CreditCard creditCard);

    /**
     * Create the order object
     *
     * @param customer the customer
     * @param shipping the shipping method
     * @return the created order.
     *         TODO Move to separate Factory?
     */
    Order createOrder(Customer customer, Shipping shipping);
}
