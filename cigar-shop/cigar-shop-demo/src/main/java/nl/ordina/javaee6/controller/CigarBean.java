package nl.ordina.javaee6.controller;

import nl.ordina.javaee6.domain.Cigar;
import nl.ordina.javaee6.service.CigarService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;

@RequestScoped
@Named
public class CigarBean {
    @Inject
    private CigarService cigarService;

    public List<Cigar> getCigars() {
        return cigarService.getCigars();
    }

    
}
