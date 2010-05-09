package nl.ordina.scea.inventory.service;

import nl.ordina.scea.inventory.domain.Inventory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@ApplicationScoped
public class AvailabilityServiceJpa implements AvailabilityService {
    @PersistenceContext private EntityManager em;

    @Override
    public int availability(int productId) {
        Inventory inventory = em.find(Inventory.class, productId);
        if (inventory==null) {
            throw new IllegalArgumentException("No product found for product id " + productId);
        }
        return inventory.getInventory();
    }
}
