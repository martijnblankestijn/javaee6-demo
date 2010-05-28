package nl.ordina.javaee6.controller;

import nl.ordina.javaee6.domain.Cigar;
import nl.ordina.javaee6.service.CigarService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model
public class CigarBean {
    private List<Cigar> cigars;

    public CigarBean(){
    }

    @Inject
    public CigarBean(CigarService cigarService) {
        cigars = cigarService.getCigars();
    }

    public List<Cigar> getCigars() {
        return cigars;
    }


}
