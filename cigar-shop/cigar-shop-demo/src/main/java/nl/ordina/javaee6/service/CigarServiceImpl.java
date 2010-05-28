package nl.ordina.javaee6.service;

import nl.ordina.javaee6.domain.Cigar;
import nl.ordina.javaee6.domain.Cigar_;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class CigarServiceImpl implements CigarService {
    @PersistenceContext private EntityManager em;

    @Override
    public List<Cigar> getCigars() {
        System.out.println("Getting list of cigars");
        return em.createQuery("select c from Cigar c").getResultList();

    }

    @Override
    public void persist(Cigar cigar) {
        System.out.println("Persisting new cigar");
        em.persist(cigar);
    }
}
