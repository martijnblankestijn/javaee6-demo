package nl.ordina.javaee6.service;

import nl.ordina.javaee6.domain.Cigar;

import java.util.List;

public interface CigarService {

    List<Cigar> getCigars();

    void persist(Cigar cigar);
}
