package nl.ordina.javaee6.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Cigar {
    @Id @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull(message = "You better not forget the price, boy")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;

    public Cigar() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
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
