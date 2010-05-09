package nl.ordina.scea.cigar.shop.web;

import nl.ordina.scea.cigar.shop.domainapi.*;
import nl.ordina.scea.cigar.shop.web.beans.CreditCardFormBean;
import nl.ordina.scea.cigar.shop.web.beans.CustomerFormBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ProcessOrderBean implements Serializable {
    @Inject
    private transient Logger logger;

    @Inject
    @CurrentShoppingCart
    private ShoppingCart shoppingCart;
    @Inject
    @SelectedShipping
    private Shipping shipping;

    @Inject
    private CreditCardFormBean creditCardForm;
    @Inject
    private CustomerFormBean customerForm;
    @Inject
    private transient CustomerFactory customerFactory;
    @Inject
    private OrderService orderService;

    private int orderNumber;


//    @Inject
//    public ProcessOrderBean(@CurrentShoppingCart ShoppingCart shoppingCart) {
//        this.shoppingCart = shoppingCart;
//    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String processOrder() {
        logger.info(("Current amount of Shopping Cart: " + shoppingCart.getTotalAmount()));


        if (logger.isLoggable(Level.FINE)) {
            logger.info("Processing new order for customer " + customerForm.getLastName()
                    + " [Credit card " + creditCardForm.getType() + " " + creditCardForm.getNumber()
                    + "], Total amount " + shoppingCart.getTotalAmount()
                    + ", [Shipping " + shipping.getDescription() + "]");
        }

        // TODO should not be done here, but where?
        Customer newCustomer = customerFactory.createNewCustomer(customerForm);

        Order order = createOrder(newCustomer);

        orderNumber = orderService.placeOrder(newCustomer, order, creditCardForm);

        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Order " + orderNumber + "  processed for customer " + customerForm.getLastName() + ".");
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Order", "Order is vastgelegd."));
        return "order_confirmed?faces-redirect=true&amp;includeViewParams=true&amp;orderNumber=" + order.getId();
    }

    private Order createOrder(Customer customer) {
        Order order = orderService.createOrder(customer, shipping);
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
            order.addProduct(shoppingCartItem.getProduct(), shoppingCartItem.getQuantity());
        }
        return order;
    }

}
