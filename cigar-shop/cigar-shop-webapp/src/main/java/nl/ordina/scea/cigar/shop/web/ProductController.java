package nl.ordina.scea.cigar.shop.web;

import nl.ordina.scea.cigar.shop.accessapi.InventoryGateway;
import nl.ordina.scea.cigar.shop.accessapi.ProductRepository;
import nl.ordina.scea.cigar.shop.accessapi.Synchronous;
import nl.ordina.scea.cigar.shop.domainapi.Product;
import nl.ordina.scea.cigar.shop.web.beans.ProductDetailsForm;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ProductController implements Serializable {
    private static final int DEFAULT_QUANTITY = 1;

    @Inject
    private transient Logger logger;
    @Inject
    private ProductRepository productRepository;
    @Inject
    @Synchronous
    private InventoryGateway inventoryService;

    private ProductDetailsForm selectedProductForm;

    /**
     * The product is selected by the user.
     *
     * @param productId the id of the product
     * @return next page
     */
    public String productSelected(int productId) {

        boolean available = inventoryService.getAvailability(productId) >= DEFAULT_QUANTITY;
        final Product product = productRepository.getProduct(productId);

        selectedProductForm = new ProductDetailsForm(product, available);

        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Number of available products for product " + selectedProductForm.getName() + ": " + available);
        }

        return "product_details";
    }

    public ProductDetailsForm getSelectedProduct() {
        return selectedProductForm;
    }
}
