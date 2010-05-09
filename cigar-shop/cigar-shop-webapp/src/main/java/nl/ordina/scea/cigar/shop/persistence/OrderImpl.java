package nl.ordina.scea.cigar.shop.persistence;

import nl.ordina.scea.cigar.shop.domainapi.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity(name = "Order")
@Table(name = "ORDERS")
public class OrderImpl implements Order {
    @Id
    @GeneratedValue
    private int id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DT")
    @NotNull
    private Date creationDate;

    @ManyToOne(targetEntity = ShippingImpl.class)
    @JoinColumn(name = "SHIPPING_ID")
    @NotNull
    private Shipping shipping;

    @ManyToOne(targetEntity = CustomerImpl.class, cascade = CascadeType.ALL)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderLineImpl> orderLines = new ArrayList<OrderLineImpl>();

    protected OrderImpl() {
    }

    public OrderImpl(Customer customer, Shipping shipping) {
        this.customer = customer;
        this.shipping = shipping;
        this.creationDate = new Date();
    }

    @Override
    public void addProduct(Product product, int quantity) {
        OrderLineImpl orderLine = new OrderLineImpl(this, product, quantity, product.getPrice());
        orderLines.add(orderLine);
    }

    @Override
    public Money getAmount() {
        Money amount = shipping.getPrice();
        for (OrderLineImpl orderLine : orderLines) {
            amount = amount.add(orderLine.getAmount());
        }
        return amount;
    }


    @Override
    public ShippingMethod getShippingMethod() {
        return shipping.getMethod();
    }

    @Override
    public List<? extends OrderLine> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public int getId() {
        return id;
    }


}
