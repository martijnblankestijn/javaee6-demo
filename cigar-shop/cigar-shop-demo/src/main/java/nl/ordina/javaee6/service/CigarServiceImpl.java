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
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cigar> getCigars() {
        System.out.println("Getting list of cigars");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Cigar> query = cb.createQuery(Cigar.class);
        final Root<Cigar> from = query.from(Cigar.class);
        query.orderBy(cb.asc(from.get(Cigar_.name)));

        return em.createQuery(query).getResultList();

    }

    @Override
    public void persist(Cigar cigar) {
        System.out.println("Persisting new cigar");
        em.persist(cigar);
    }
}
