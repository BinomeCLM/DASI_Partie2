package fr.insalyon.b3427.positif.dao;

import fr.insalyon.b3427.positif.modele.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author B3427
 */
public class PrestationDAO {
    public Prestation findById(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager(); 
        return em.find(Prestation.class, id); 
    }
    public void persist(Prestation pres){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(pres);
    }
    public void merge(Prestation pres){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(pres);
    }
    public void remove(Prestation pres){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.remove(pres);
    }
    public List<Prestation> getHistorique(Client cl){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select p from Prestation p where p.client=:cl");
        q.setParameter("cl", cl);
        
        return (List<Prestation>) q.getResultList();
    }
    public Prestation getPrestation(Employe emp){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select p from Prestation p where p.employe=:emp and p.heureDebut=null");
        q.setParameter("emp", emp);
        List results = q.getResultList();
        if(!results.isEmpty()){
            return (Prestation) results.get(0);
        }
        return null;
    }
}
