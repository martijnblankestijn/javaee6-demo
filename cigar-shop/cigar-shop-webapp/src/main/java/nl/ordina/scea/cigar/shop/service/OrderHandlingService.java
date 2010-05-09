package nl.ordina.scea.cigar.shop.service;

import nl.ordina.scea.cigar.shop.accessapi.CreditCardProcessor;
import nl.ordina.scea.cigar.shop.accessapi.Mailer;
import nl.ordina.scea.cigar.shop.accessapi.OrderRepository;
import nl.ordina.scea.cigar.shop.accessapi.ShippingService;
import nl.ordina.scea.cigar.shop.domainapi.*;

import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Stateless
public class OrderHandlingService implements OrderService {
    @Inject
    private CreditCardProcessor processor;
    @Inject
    private Mailer mailer;
    @Inject
    private
    OrderRepository orderRepository;

    @Inject
    @Any
    private Instance<ShippingService> anyShippingService;


    @Override
    public int placeOrder(Customer customer, Order order, CreditCard creditCard) {
        orderRepository.persist(order);

        processor.authorizePayment(creditCard, order.getAmount(), "Order number " + order.getId());

        mailer.sendMail(customer.getEmailAddress(), "Order confirmation", "You're order has been placed. The order number is " + order.getId());

        ShippingService shippingService = selectShippingService(order.getShippingMethod());
        shippingService.shipOrder(null);
        return order.getId();
    }

    /**
     * Select the shipping service with supports the corresponding shipping method.
     *
     * @param shippingMethod the method of shipping
     * @return the selected shipping service
     */
    private ShippingService selectShippingService(ShippingMethod shippingMethod) {
        return anyShippingService.select(new ShippingMethodQualifier(shippingMethod)).get();
    }

    @Override
    public Order createOrder(Customer customer, Shipping shipping) {
        return orderRepository.createOrder(customer, shipping);
    }

}
