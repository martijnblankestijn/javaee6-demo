package nl.ordina.scea.cigar.shop.domain;

import nl.ordina.scea.cigar.shop.domainapi.Customer;
import nl.ordina.scea.cigar.shop.domainapi.CustomerFactory;
import nl.ordina.scea.cigar.shop.persistence.CustomerImpl;

import javax.enterprise.context.ApplicationScoped;

/**
 * Factory for customer object.
 */
@ApplicationScoped
public class CustomerFactoryImpl implements CustomerFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Customer createNewCustomer(Customer customer) {
        CustomerImpl newCustomer = new CustomerImpl(customer.getLastName());
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setAddressLine(customer.getAddressLine());
        newCustomer.setCity(customer.getCity());
        newCustomer.setState(customer.getState());
        newCustomer.setZipCode(customer.getZipCode());
        newCustomer.setEmailAddress(customer.getEmailAddress());
        return newCustomer;
    }
}
