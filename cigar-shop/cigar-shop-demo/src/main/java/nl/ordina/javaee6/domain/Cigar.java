package nl.ordina.javaee6.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Cigar {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    private BigDecimal price;

    protected Cigar() {
    }

    public Cigar(final String name, final BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Cigar{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
