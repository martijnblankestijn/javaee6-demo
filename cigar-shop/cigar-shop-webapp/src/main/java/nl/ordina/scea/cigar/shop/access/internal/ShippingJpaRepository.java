package nl.ordina.scea.cigar.shop.access.internal;

import nl.ordina.scea.cigar.shop.accessapi.ShippingRepository;
import nl.ordina.scea.cigar.shop.domainapi.Shipping;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * JPA implementation of the Shipping Repository.
 * <p/>
 * Made application scoped (just because you can ;-)) to fetch
 * the shipping alternatives at the start of the application
 * and return the fetched alternatives in the rest of the lifetime
 * of the application.
 */
@ApplicationScoped
public class ShippingJpaRepository implements ShippingRepository, Serializable {
    @PersistenceContext
    private EntityManager em;

    private List<Shipping> shippingAlternatives;


    /**
     * Initialization: fetch all shipping alternatives at startup.
     */
    @PostConstruct
    public void init() {
        shippingAlternatives = em.createQuery("select s from Shipping s", Shipping.class).getResultList();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Produces
    @Named
    public List<Shipping> getShippingAlternatives() {
        return shippingAlternatives;
    }
}
