package nl.ordina.scea.cigar.shop.access.internal;

import nl.ordina.scea.cigar.shop.accessapi.ProductRepository;
import nl.ordina.scea.cigar.shop.domainapi.Product;
import nl.ordina.scea.cigar.shop.domainapi.ProductCategory;
import nl.ordina.scea.cigar.shop.persistence.ProductDefinition;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * JPA Implementation of the product repository.
 */
@RequestScoped
public class ProductJpaRepository implements ProductRepository, Serializable {
    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public Product getProduct(int productId) {
        return em.find(ProductDefinition.class, productId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getProductsFor(ProductCategory category) {
        final String query = "select p from Product p where p.category = :category";
        return em.createQuery(query, Product.class).setParameter("category", category).getResultList();
    }
}
