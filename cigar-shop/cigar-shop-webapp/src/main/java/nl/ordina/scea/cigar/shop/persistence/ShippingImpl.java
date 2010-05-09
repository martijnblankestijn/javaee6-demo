package nl.ordina.scea.cigar.shop.persistence;

import nl.ordina.scea.cigar.shop.domainapi.Money;
import nl.ordina.scea.cigar.shop.domainapi.Shipping;
import nl.ordina.scea.cigar.shop.domainapi.ShippingMethod;

import javax.persistence.*;

@Entity(name = "Shipping")
@Table(name = "SHIPPING")
public class ShippingImpl implements Shipping {
    @Id
    private int id;

    @Enumerated
    @Column(name = "SHIPPING_METHOD")
    private ShippingMethod method;
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "PRICE"))})
    private Money price;
    private String description;

    public ShippingImpl() {
    }

    public ShippingImpl(final int id, final ShippingMethod method, final Money price, final String description) {
        this.method = method;
        this.price = price;
        this.description = description;
        this.id = id;
    }

    @Override
    public ShippingMethod getMethod() {
        return method;
    }

    @Override
    public Money getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShippingImpl shipping = (ShippingImpl) o;

        return id == shipping.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
