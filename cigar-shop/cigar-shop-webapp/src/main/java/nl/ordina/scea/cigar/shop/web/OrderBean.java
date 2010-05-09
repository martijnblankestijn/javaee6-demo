package nl.ordina.scea.cigar.shop.web;

import nl.ordina.scea.cigar.shop.accessapi.OrderRepository;
import nl.ordina.scea.cigar.shop.domainapi.Order;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class OrderBean {
    @Inject
    private transient Logger logger;
    @Inject
    private
    OrderRepository orderRepository;

    private Order order;
    private Integer orderNumber;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String retrieve(ComponentSystemEvent event) {
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Retrieving order " + orderNumber);
        }
        order = orderRepository.fetchById(orderNumber);
        return null;
    }

}
