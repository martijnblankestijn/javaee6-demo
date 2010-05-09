package nl.ordina.scea.cigar.shop.persistence;

import nl.ordina.scea.cigar.shop.domainapi.Manufacturer;
import nl.ordina.scea.cigar.shop.domainapi.Money;
import nl.ordina.scea.cigar.shop.domainapi.Product;
import nl.ordina.scea.cigar.shop.domainapi.ProductCategory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Definition of a product.
 */
@Entity(name = "Product")
@Table(name = "PRODUCT_DEFINITION")
public class ProductDefinition implements Product, Serializable {

    @Id
    @NotNull
    private int id;

    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @NotNull
    @Size(min = 2, max = 100)
    private String description;

    @Enumerated
    private ProductCategory category;

    @NotNull
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "PRICE"))})
    private Money price;

    @ManyToOne(optional = false, targetEntity = ManufacturerImpl.class)
    private Manufacturer manufacturer;

    protected ProductDefinition() {
    }

    public ProductDefinition(ProductCategory category, Manufacturer manufacturer) {
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public Money getPrice() {
        return price;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    @Override
    public ProductCategory getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDefinition that = (ProductDefinition) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ProductDefinition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
