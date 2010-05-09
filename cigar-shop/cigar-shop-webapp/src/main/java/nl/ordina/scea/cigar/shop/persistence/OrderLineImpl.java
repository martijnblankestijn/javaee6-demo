package nl.ordina.scea.cigar.shop.persistence;

import nl.ordina.scea.cigar.shop.domainapi.Money;
import nl.ordina.scea.cigar.shop.domainapi.Order;
import nl.ordina.scea.cigar.shop.domainapi.OrderLine;
import nl.ordina.scea.cigar.shop.domainapi.Product;

import javax.persistence.*;

@Entity(name = "OrderLine")
@Table(name = "ORDER_LINE")
public class OrderLineImpl implements OrderLine {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false, targetEntity = ProductDefinition.class)
    private Product product;

    private int quantity;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "PRICE"))})
    private Money price;

    @ManyToOne(targetEntity = OrderImpl.class)
    private Order order;

    protected OrderLineImpl() {
    }

    public OrderLineImpl(Order order, Product product, int quantity, Money price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public Money getAmount() {
        return price.times(quantity);
    }

    @Override
    public String getProductName() {
        return product.getName();
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public Money getPrice() {
        return price;
    }
}
