package nl.ordina.javaee6.controller;

import nl.ordina.javaee6.domain.Cigar;
import nl.ordina.javaee6.service.CigarService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Model
public class CreateCigarBean {
    private CigarService cigarService;
    private FacesContext ctx;
    private ExternalContext external;
    private Cigar cigar;

    public CreateCigarBean() {
    }

    @Inject
    public CreateCigarBean(@New Cigar cigar, ExternalContext external, FacesContext ctx, CigarService cigarService) {
        this.external = external;
        this.ctx = ctx;
        this.cigarService = cigarService;
        this.cigar = cigar;
    }


    public String create() {
        cigarService.persist(cigar);

        external.getFlash().setKeepMessages(true);
        ctx.addMessage(null, new FacesMessage("Give that man a cigar!!"));
        return "cigars?faces-redirect=true";
    }

    public Cigar getCigar() {
        return cigar;
    }

    @Produces
    @RequestScoped
    public FacesContext createFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
    @RequestScoped
    public ExternalContext createExternalContext(FacesContext context) {
        return context.getExternalContext();
    }
}
