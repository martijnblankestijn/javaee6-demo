package nl.ordina.javaee6.controller;

import nl.ordina.javaee6.domain.Cigar;
import nl.ordina.javaee6.service.CigarService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@RequestScoped
@Named
public class CreateCigarBean {
    @Inject private CigarService cigarService;

    private String name;
    private BigDecimal price;

    public String create() {
        Cigar cigar = new Cigar(name, price);
        cigarService.persist(cigar);
        return "cigars";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
