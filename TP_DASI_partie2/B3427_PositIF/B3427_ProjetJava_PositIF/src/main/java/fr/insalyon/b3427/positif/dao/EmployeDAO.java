package fr.insalyon.b3427.positif.dao;

import fr.insalyon.b3427.positif.modele.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author B3427
 */
public class EmployeDAO {
    public Employe findById(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Employe.class, id);
    }
    public void persist(Employe cl){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(cl);
    }
    public void merge(Employe cl){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(cl);
    }
    public void remove(Employe cl){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.remove(cl);
    }
    public Employe getANotBusyEmploye(String talent){
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        Query q = em.createQuery("select e from Employe e where e.busy=false and e.talent=:talent order by e.nbPrestation asc", Employe.class);
        q.setParameter("talent", talent);
        
        List results = q.getResultList();
        if(!results.isEmpty()){
            return (Employe) results.get(0);
        }
        return null;
    }
    public List<Employe> getListEmploye(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("select e from Employe e", Employe.class);
        List results = q.getResultList();
        return results;
    }
}
