package nl.ordina.javaee6.controller;

import nl.ordina.javaee6.domain.Cigar;
import nl.ordina.javaee6.service.CigarService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class CigarBean {
    @Inject
    private CigarService cigarService;

    private List<Cigar> cigars;

    @PostConstruct
    private void init() {
        cigars = cigarService.getCigars();
    }

    @PreDestroy
    public void end() {
        System.out.println("Clean up of managed bean");
    }


    public List<Cigar> getCigars() {
        return cigars;
    }


}
