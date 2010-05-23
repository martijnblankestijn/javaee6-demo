package nl.ordina.javaee6.service;

import nl.ordina.javaee6.domain.Cigar;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.List;

@Decorator
public class CigarServiceLoggingDecorater implements CigarService {
    private final CigarService delegate;

    @Inject
    public CigarServiceLoggingDecorater(@Delegate CigarService cigarService) {
        this.delegate = cigarService;
    }

    @Override
    public List<Cigar> getCigars() {
        System.out.println("Doing usefull work before getting cigars");
        return delegate.getCigars();
    }

    @Override
    public void persist(Cigar cigar) {
        System.out.println("Before persist cigar " + cigar);
        delegate.persist(cigar);
    }
}
