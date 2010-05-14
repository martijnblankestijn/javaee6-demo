package nl.ordina.javaee6.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Cigar {
    @Id
    @GeneratedValue private long id;

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
}
