package nl.ordina.scea.cigar.shop.web;

import nl.ordina.scea.cigar.shop.accessapi.ProductRepository;
import nl.ordina.scea.cigar.shop.domainapi.Product;
import nl.ordina.scea.cigar.shop.domainapi.ProductCategory;
import nl.ordina.scea.cigar.shop.web.beans.ProductSearchResultModel;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class SearchCatalog implements Serializable {
    @Inject
    private transient Logger logger;
    @Inject
    private ProductRepository productRepository;


    private ProductCategory currentCategory;

    /**
     * Initialize the selection of a product category.
     *
     * @return the next page
     */
    public String initializeProductCategorySelection() {
        currentCategory = null;
        return "category_select";
    }

    /**
     * Process the product category event.
     *
     * @param value the change event
     */
    public void processCategorySelection(ValueChangeEvent value) {
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Changing value from " + value.getOldValue() + " to " + value.getNewValue());
        }
        currentCategory = (ProductCategory) value.getNewValue();
    }

    public List<ProductSearchResultModel> getProducts() {
        if (currentCategory != null) {
            return retrieveProducts();
        }
        return new ArrayList<ProductSearchResultModel>();
    }

    private List<ProductSearchResultModel> retrieveProducts() {
        final List<ProductSearchResultModel> searchResultModels = new ArrayList<ProductSearchResultModel>();
        final List<Product> found = productRepository.getProductsFor(currentCategory);
        for (Product product : found) {
            ProductSearchResultModel searchResultModel = new ProductSearchResultModel();
            searchResultModel.setId(product.getId());
            searchResultModel.setName(product.getName());
            searchResultModels.add(searchResultModel);
        }
        return searchResultModels;
    }


    public ProductCategory getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(ProductCategory currentCategory) {
        this.currentCategory = currentCategory;
    }

    public boolean isCategorySelected() {
        return currentCategory != null;
    }

}
