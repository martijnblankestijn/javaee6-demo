package nl.ordina.javaee6.service;

import nl.ordina.javaee6.domain.Cigar;

import java.util.List;


public interface CigarService {
    /** List all available cigars.*/
    List<Cigar> getCigars();

    /** Persist a cigar. */
    void persist(Cigar cigar);
}
