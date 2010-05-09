package nl.ordina.scea.cigar.shop.domainapi;

/**
 * Factory for customer objects.
 */
public interface CustomerFactory {
    /**
     * Create a new customer based on an existing customer.
     *
     * @param customer the customer
     * @return new customer
     */
    Customer createNewCustomer(Customer customer);
}
