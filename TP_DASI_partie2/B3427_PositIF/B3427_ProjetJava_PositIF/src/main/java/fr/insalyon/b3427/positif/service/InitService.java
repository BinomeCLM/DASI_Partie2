package fr.insalyon.b3427.positif.service;

import fr.insalyon.b3427.positif.dao.JpaUtil;
import fr.insalyon.b3427.positif.dao.*;
import fr.insalyon.b3427.positif.modele.*;
import java.util.Date;

/**
 * 
 * @author B3427
 */
public class InitService {
    public InitService(){
        
    }
    public void newMedium(Medium med){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        MediumDAO mediumDAO = new MediumDAO();
        mediumDAO.persist(med);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    public void newEmploye(Employe emp){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        EmployeDAO employeDAO = new EmployeDAO();
        employeDAO.persist(emp);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    public void fillDB(){
        Astrologue as = new Astrologue();
        as.setNom("Mm MMmm");
        as.setBio("Aeds qsdkqsd ds dqpsqd dqs, sqdqsd sqpvee.");
        as.setEcole("INSA");
        as.setPromotion("1998");
        newMedium(as);
        
        Tarologue tl = new Tarologue();
        tl.setNom("Mdm MMdmm");
        tl.setBio("A1deds qsddqkqsd ds dqpsqqd dqs, sqdqsd sqpvee.");
        tl.setCartes("Tarot de Lyon");
        newMedium(tl);
        
        Voyant voy = new Voyant();
        voy.setNom("Mme Irma");
        voy.setBio("La bio");
        voy.setSupport("Boule de cristal");
        newMedium(voy);
        
        
        Employe emp = new Employe();
        emp.setNomEmploye("Jean");
        emp.setTalent("Astrologue");
        emp.setBusy(false);
        newEmploye(emp);
        
        Employe emp1 = new Employe();
        emp1.setNomEmploye("Michel");
        emp1.setTalent("Tarologue");
        emp1.setBusy(false);
        newEmploye(emp1);
        
        Employe emp2 = new Employe();
        emp2.setNomEmploye("Jean-Marie");
        emp2.setTalent("Voyant");
        emp2.setBusy(false);
        newEmploye(emp2);
        
        Client cl = new Client("Mr", "Christophe", "Hirt", new Date(), "15 rue Patrick Timsit", "0132584536", "a");
        ClientService cs = new ClientService();
        cs.inscription(cl);
    }
}
