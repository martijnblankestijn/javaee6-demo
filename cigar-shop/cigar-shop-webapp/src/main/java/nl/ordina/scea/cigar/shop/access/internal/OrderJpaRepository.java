package nl.ordina.scea.cigar.shop.access.internal;

import nl.ordina.scea.cigar.shop.accessapi.OrderRepository;
import nl.ordina.scea.cigar.shop.domainapi.Customer;
import nl.ordina.scea.cigar.shop.domainapi.Order;
import nl.ordina.scea.cigar.shop.domainapi.Shipping;
import nl.ordina.scea.cigar.shop.persistence.OrderImpl;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA implementation of the Order Repository.
 */
@RequestScoped
public class OrderJpaRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public Order fetchById(Integer orderNumber) {
        return em.find(OrderImpl.class, orderNumber);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void persist(Order order) {
        em.persist(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order createOrder(Customer customer, Shipping shipping) {
        return new OrderImpl(customer, shipping);
    }
}
