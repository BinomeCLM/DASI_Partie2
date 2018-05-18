package fr.insalyon.b3427.positif.service;

import fr.insalyon.b3427.positif.dao.ClientDAO;
import fr.insalyon.b3427.positif.dao.EmployeDAO;
import fr.insalyon.b3427.positif.dao.JpaUtil;
import fr.insalyon.b3427.positif.dao.MediumDAO;
import fr.insalyon.b3427.positif.dao.PrestationDAO;
import fr.insalyon.b3427.positif.modele.*;
import fr.insalyon.b3427.positif.util.AstroTest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author B3427
 */
public class EmployeService {
    public Employe getEmploye(Long employeId){
        EmployeDAO employeDAO = new EmployeDAO();
        JpaUtil.creerEntityManager();
        Employe emp = employeDAO.findById(employeId);
        JpaUtil.fermerEntityManager();
        return emp;
    }
    public Client getClient(Long userId){
        ClientDAO clientDAO = new ClientDAO();
        JpaUtil.creerEntityManager();
        Client cl = clientDAO.findById(userId);
        JpaUtil.fermerEntityManager();
        return cl;
    }
    public Prestation getWaitingPrestation(Employe emp){
        PrestationDAO prestationDAO = new PrestationDAO();
        JpaUtil.creerEntityManager();
        Prestation pres = prestationDAO.getPrestation(emp);
        JpaUtil.fermerEntityManager();
        return pres;
    }
    public List<String> getPrediction(Client cl, int amour, int sante, int travail){
        try {
            AstroTest aT;
            aT = new AstroTest(AstroTest.MA_CLE_ASTRO_API);
            System.out.println(sante + ' ' + amour + ' ' + travail);
            return aT.getPredictions(cl.getCouleur(), cl.getAnimalTotem(), amour, sante, travail);
        } catch (IOException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Prestation> getHistoric(Client cl){
        PrestationDAO prestationDAO = new PrestationDAO();
        
        JpaUtil.creerEntityManager();
        List<Prestation> lp = prestationDAO.getHistorique(cl);
        JpaUtil.fermerEntityManager();
        
        return lp;
    }
    public void startPrestation(Prestation pres){
        pres.setHeureDebut(new Date());
        PrestationDAO prestationDAO = new PrestationDAO();
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        prestationDAO.merge(pres);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    public void stopPrestation(Prestation pres){
        pres.setHeureFin(new Date());
        Employe emp = pres.getEmploye();
        emp.setBusy(false);
        
        EmployeDAO empDAO = new EmployeDAO();
        PrestationDAO prestationDAO = new PrestationDAO();
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        prestationDAO.merge(pres);
        empDAO.merge(emp);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    public List<Employe> getListEmploye(){
        EmployeDAO employeDAO = new EmployeDAO();
        
        JpaUtil.creerEntityManager();
        List<Employe> le = employeDAO.getListEmploye();
        JpaUtil.fermerEntityManager();
        
        return le;
    }
    public List<Medium> getListMedium(){
        MediumDAO mediumDAO = new MediumDAO();
        
        JpaUtil.creerEntityManager();
        List<Medium> lm = mediumDAO.getListMedium();
        JpaUtil.fermerEntityManager();
        
        return lm;
    }
}
