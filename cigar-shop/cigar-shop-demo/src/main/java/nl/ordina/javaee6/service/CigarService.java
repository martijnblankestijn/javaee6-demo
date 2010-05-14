package nl.ordina.javaee6.service;

import nl.ordina.javaee6.domain.Cigar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class CigarService {
    @PersistenceContext
    private EntityManager em;

    public List<Cigar> getCigars() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cigar> query = cb.createQuery(Cigar.class);
        Root<Cigar> root = query.from(Cigar.class);

        query.orderBy(cb.asc(root.get("name")));

            return em.createQuery(query).getResultList();
//          return em.createQuery("select c from Cigar c", Cigar.class).getResultList();
    }

    public void persist(Cigar cigar) {
        em.persist(cigar);
    }
}
