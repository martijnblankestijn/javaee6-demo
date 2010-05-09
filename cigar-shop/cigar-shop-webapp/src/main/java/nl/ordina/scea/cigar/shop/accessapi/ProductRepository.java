package nl.ordina.scea.cigar.shop.accessapi;

import nl.ordina.scea.cigar.shop.domainapi.Product;
import nl.ordina.scea.cigar.shop.domainapi.ProductCategory;

import java.util.List;

/**
 * Repository for products.
 */
public interface ProductRepository {
    /**
     * @param productId the id of the product
     * @return the found product
     */
    Product getProduct(int productId);

    /**
     * Retrieve products for a certain category
     *
     * @param category the category to searh for
     * @return a list of products
     */
    List<Product> getProductsFor(ProductCategory category);
}
