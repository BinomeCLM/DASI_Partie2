package fr.insalyon.b3427.positif.dao;

import fr.insalyon.b3427.positif.modele.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author B3427
 */
public class ClientDAO {
    public Client findByCourriel(String courriel){
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        Query q = em.createQuery("select c from Client c where c.courriel=:cour", Client.class);
        q.setParameter("cour", courriel);
        
        List results = q.getResultList();
        if(!results.isEmpty()){
            return (Client) results.get(0);
        }
        return null;
    }
    public Client findById(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Client.class, id);
    }
    public void persist(Client cl){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(cl);
    }
    public void merge(Client cl){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(cl);
    }
    public void remove(Client cl){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.remove(cl);
    }
}
