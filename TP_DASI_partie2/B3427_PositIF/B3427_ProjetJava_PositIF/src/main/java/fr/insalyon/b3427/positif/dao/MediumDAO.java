package fr.insalyon.b3427.positif.dao;

import fr.insalyon.b3427.positif.modele.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author B3427
 */
public class MediumDAO {
    public Medium findById(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Medium.class, id);
    }
    public void persist(Medium med){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(med);
    }
    public void merge(Medium med){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(med);
    }
    public void remove(Medium med){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.remove(med);
    }
    public List<Medium> getListMedium(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select m from Medium m");
        
        return (List<Medium>) q.getResultList();
    }
}
