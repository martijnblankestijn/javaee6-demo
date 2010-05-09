package nl.ordina.scea.cigar.shop.web;

import nl.ordina.scea.cigar.shop.accessapi.InventoryGateway;
import nl.ordina.scea.cigar.shop.accessapi.ShippingRepository;
import nl.ordina.scea.cigar.shop.accessapi.Synchronous;
import nl.ordina.scea.cigar.shop.domainapi.*;
import nl.ordina.scea.cigar.shop.web.beans.ProductDetailsForm;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIData;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "shoppingCart")
@ConversationScoped
public class ShoppingCartBean implements Serializable {
    @Inject
    private transient Logger logger;

    @Inject
    private Conversation conversation;
    @Inject
    private ShoppingCart shoppingCart;
    @Inject
    @Synchronous
    private InventoryGateway inventoryGateway;
    @Inject
    private ShippingRepository shippingRepository;

    private List<ShoppingCartItem> items;
    private Shipping shipping;

    @PostConstruct
    public void init() {
        shipping = shippingRepository.getShippingAlternatives().get(0);
    }

    @Produces
    @CurrentShoppingCart
    public ShoppingCart currentShoppingCart() {
        return shoppingCart;
    }

    public String addProduct(ProductDetailsForm detailsForm) {
        if (conversation.isTransient()) {
            logger.fine("Starting conversation");
            conversation.begin();
        }
        final Product product = detailsForm.getProduct();
        logger.info("Adding product " + detailsForm.getProduct() + " to cart.");

        final int available = inventoryGateway.getAvailability(product.getId());
        if (available == 0) {
            throw new IllegalArgumentException("No products available");
        }
        shoppingCart.addProduct(product);
        items = null;

        return returnToShoppingCart();
    }

    private String returnToShoppingCart() {
        return "shopping_cart";
    }

    public List<ShoppingCartItem> getItems() {
        if (items == null) {
            items = shoppingCart.getItems();
        }
        return items;
    }

    public Money getTotalAmount() {
        Money totalAmount = Money.ZERO;
        for (ShoppingCartItem item : items) {
            totalAmount = totalAmount.add(item.getCartItemAmount());
        }
        if (shipping != null) {
            final Money shippingPrice = shipping.getPrice();
            return totalAmount.add(shippingPrice);
        }
        return totalAmount;
    }

    public void remove(Product product) {
        shoppingCart.remove(product);
        items = null;
    }

    public void processQuantityChange(ValueChangeEvent value) {
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Changing value from " + value.getOldValue() + " to " + value.getNewValue());
        }
        // TODO do not know with which item changed so update them all.
        for (ShoppingCartItem item : items) {
            shoppingCart.changeQuantity(item.getProduct(), item.getQuantity());
        }

    }

    public void processShippingSelection(ValueChangeEvent value) {
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Changing value from " + value.getOldValue() + " to " + value.getNewValue());
        }
        shipping = (Shipping) value.getNewValue();
    }


    public String checkout() {
        updateShoppingCartItems();
        items = null;
        getItems();
        return "enter_customer_details";
    }

    public void updateElementValues() {
        updateShoppingCartItems();
        items = null;
    }

    private void updateShoppingCartItems() {
        final UIViewRoot uiViewRoot = FacesContext.getCurrentInstance().getViewRoot();
        UIData uIData = (UIData) uiViewRoot.findComponent("shopping_cart:shoppingCartItems");
        logger.info("Updating the values for component " + uIData);
        if (uIData != null) {
            HtmlDataTable myTable = (HtmlDataTable) uIData;

            for (int i = 0; i < myTable.getRowCount(); i++) {
                myTable.setRowIndex(i);
                ShoppingCartItem modifiedElement = (ShoppingCartItem) myTable.getRowData();
                logger.info("Quantity " + modifiedElement.getQuantity());
                logger.info("Product " + modifiedElement.getProduct());

                final int available = inventoryGateway.getAvailability(modifiedElement.getProduct().getId());
                if (modifiedElement.getQuantity() > available) {
                    throw new IllegalArgumentException("Request for " + modifiedElement.getQuantity() + ". Available " + available);
                }
                shoppingCart.changeQuantity(modifiedElement.getProduct(), modifiedElement.getQuantity());
            }
            //make sure to set the row Index back to -1!!! ==> But WHY?
            myTable.setRowIndex(-1);
        }
    }


    @Produces
    @SelectedShipping
    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public boolean isEmpty() {
        return getItems().isEmpty();
    }

    public void end() {
        conversation.end();

    }
}
