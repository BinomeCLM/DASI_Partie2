package fr.insalyon.b3427.positif.service;

import fr.insalyon.b3427.positif.dao.JpaUtil;
import fr.insalyon.b3427.positif.dao.*;
import fr.insalyon.b3427.positif.modele.*;
import fr.insalyon.b3427.positif.util.AstroTest;
import fr.insalyon.b3427.positif.util.SendMail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author B3427
 */
public class ClientService {
    public ClientService(){
        
    }
    public boolean inscription(Client client) {
        ClientDAO clientDAO = new ClientDAO();
        JpaUtil.creerEntityManager();
        if(clientDAO.findByCourriel(client.getCourriel())==null){
            try {
                AstroTest aT;
                aT = new AstroTest(AstroTest.MA_CLE_ASTRO_API);
            
                List<String> result = aT.getProfil(client.getPrenom(), client.getDateNaissance());
                
                client.setSigneZodiaque(result.get(0));
                client.setSigneChinois(result.get(1));
                client.setCouleur(result.get(2));
                client.setAnimalTotem(result.get(3));
                
                JpaUtil.ouvrirTransaction();
                clientDAO.persist(client);
                JpaUtil.validerTransaction();
                
                SendMail.send(client.getCourriel(), "Bienvenue chez Posit'IF", "Bonjour "+client.getPrenom()+",\n"
                + "Nous vous confirmons votre inscription au service POSIT'IF. Votre numéro de client est: "+client.getId()+".");
                JpaUtil.fermerEntityManager();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SendMail.send(client.getCourriel(), "Bienvenue chez Posit'IF", "Bonjour "+client.getPrenom()+",\n"
                + "Votre inscription aus service a malheureusement échoué... Merci de recommencer ultérieurement.");
        JpaUtil.fermerEntityManager();
        return false;
    }
    public Client connexion(String courriel){
        ClientDAO clientDAO = new ClientDAO();
        JpaUtil.creerEntityManager();
        Client client = clientDAO.findByCourriel(courriel);
        JpaUtil.fermerEntityManager();
        if(client==null){
            return null;
        }
        else {
            return client;
        }
    }
    public List<Medium> getListMedium(){
        JpaUtil.creerEntityManager();
        MediumDAO mediumDAO = new MediumDAO();
        List<Medium> mediums = mediumDAO.getListMedium();
        JpaUtil.fermerEntityManager();
        return mediums;
    }
    public Client getClient(Long userId){
        ClientDAO clientDAO = new ClientDAO();
        JpaUtil.creerEntityManager();
        Client cl = clientDAO.findById(userId);
        JpaUtil.fermerEntityManager();
        return cl;
    }
    public boolean demanderVoyance(Client cl, Long mediumId){
        JpaUtil.creerEntityManager();
        MediumDAO mediumDAO = new MediumDAO();
        Medium med = mediumDAO.findById(mediumId);
        med.incNbPrestation();
        
        EmployeDAO employeDAO = new EmployeDAO();
        Employe emp = employeDAO.getANotBusyEmploye(med.getTalent());
        if(emp==null){
            return false;
        }
        JpaUtil.ouvrirTransaction();
        
        
        med.incNbPrestation();
        
        emp.setBusy(true);
        emp.incNbPrestation();
        employeDAO.merge(emp);
        
        
        PrestationDAO prestationDAO = new PrestationDAO();
        Prestation pres = new Prestation();
        pres.setClient(cl);
        pres.setMedium(med);
        pres.setEmploye(emp);
        
        prestationDAO.persist(pres);
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        System.out.println("Pour employé "+emp.getNomEmploye()+" (#"+emp.getId()+"): Voyance demandé pour client "+cl.getPrenom()+" "+cl.getNom()+" (#"+cl.getId()+
                "), Médium: "+med.getNom());
        
        return true;
    }
}
