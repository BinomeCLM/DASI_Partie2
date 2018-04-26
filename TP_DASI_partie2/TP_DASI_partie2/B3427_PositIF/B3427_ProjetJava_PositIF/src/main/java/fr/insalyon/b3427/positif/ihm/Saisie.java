package fr.insalyon.b3427.positif.ihm;

import fr.insalyon.b3427.positif.dao.JpaUtil;
import fr.insalyon.b3427.positif.modele.Astrologue;
import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Employe;
import fr.insalyon.b3427.positif.modele.Medium;
import fr.insalyon.b3427.positif.modele.Prestation;
import fr.insalyon.b3427.positif.modele.Tarologue;
import fr.insalyon.b3427.positif.modele.Voyant;
import fr.insalyon.b3427.positif.service.ClientService;
import fr.insalyon.b3427.positif.service.EmployeService;
import fr.insalyon.b3427.positif.service.InitService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DASI Team
 */
public class Saisie {
    public static String lireChaine(String invite) {
        String chaineLue = null;
        System.out.print(invite);
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            chaineLue = br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        return chaineLue;
    }
    public static Integer lireInteger(String invite) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
        }
        return valeurLue;
    }
    public static Integer lireInteger(String invite, List<Integer> valeursPossibles) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
            if (!(valeursPossibles.contains(valeurLue))) {
                System.out.println("/!\\ Erreur de saisie - Valeur non-autorisée /!\\");
                valeurLue = null;
            }
        }
        return valeurLue;
    }
    public static void pause() {
        lireChaine("--PAUSE--");
    }
    public static void main(String[] args) {
        JpaUtil.init();
        InitService is = new InitService();
        is.fillDB();
        home();
        JpaUtil.destroy();
    }
    public static void home() {
        System.out.println("1. Client\n2. Employe\n3. Quitter");
        int entree = lireInteger("Votre choix: ");
        if(entree==1){
            unloggedClient();
        }
        else if(entree==2){
            employe();
        }
        else if(entree==3){
            
        }
        else {
            home();
        }
    }
    public static void employe() {
        int entree = lireInteger("ID: ");
        
        EmployeService es = new EmployeService();
        Employe emp = es.getEmploye(Long.valueOf(entree));
        System.out.println(emp.getId()+" "+emp.getNomEmploye());
        
        Prestation pres = es.getWaitingPrestation(emp);
        if(pres==null){
            
        }
        else {
            Client cl = pres.getClient();
            Medium med = pres.getMedium();
            System.out.println(cl.getPrenom()+" "+cl.getNom());
            System.out.println(med.getNom());
            
            List<Prestation> press = es.getHistoric(cl);
            for(Prestation p:press){
                System.out.println(p.getHeureDebut()+" "+p.getHeureFin());
            }
            
            List<String> ls = es.getPrediction(cl, 1, 3, 5);
            System.out.println(ls.get(0));
            System.out.println(ls.get(1));
            System.out.println(ls.get(2));
            
            es.startPrestation(pres);
            
            pause();
            
            es.stopPrestation(pres); 
        }
    } 
    public static void unloggedClient() {
        System.out.println("1. Connexion\n2. Inscription");
        int entree = lireInteger("Votre choix: ", Arrays.asList(1,2));
        if(entree==1){
            connexion();
        }
        else if(entree==2){
            inscription();
        }
        else {
            home();
        }
    }
    public static void connexion() {
        String entree = lireChaine("Courriel: ");
        ClientService cs = new ClientService();
        Client cl = cs.connexion(entree);
        if(cl==null){
            unloggedClient();
        }
        else {
            client(cl);
        }
    }
    public static void inscription() {
        String civilite = lireChaine("Civilité: ");
        String prenom = lireChaine("Prenom: ");
        String nom = lireChaine("Nom: ");
        String addrPost = lireChaine("Adresse postale: ");
        String tel = lireChaine("Telephone: ");
        String mail = lireChaine("Courriel: ");
        
        Client cl = new Client(civilite, prenom, nom, new Date(), addrPost, tel, mail);
        
        ClientService cs = new ClientService();
        cs.inscription(cl);
        unloggedClient();
    }
    public static void client(Client cl) {
        ClientService cs = new ClientService();
        System.out.println("("+cl.getId()+") "+cl.getPrenom()+" "+cl.getNom());
        System.out.println("1. Profil\n2. Liste mediums\n3. Retour");
        
        int entree = lireInteger("Votre choix: ", Arrays.asList(1,2,3));
        if(entree==1){
            profil(cl);
        }
        else if(entree==2){
            mediums(cl);
        }
        else {
            home();
        }
    }
    public static void profil(Client cl) {
        System.out.println("Nom: "+cl.getCivilite()+" "+cl.getPrenom()+" "+cl.getNom());
        System.out.println("Date de naissance: "+cl.getDateNaissance());
        System.out.println("Adresse postale: "+cl.getAdressePostale());
        System.out.println("Telephone: "+cl.getTelephone());
        System.out.println("Courriel: "+cl.getCourriel());
        System.out.println("Signe zodiaque: "+cl.getSigneZodiaque());
        System.out.println("Signe chinois: "+cl.getSigneChinois());
        System.out.println("Couleur: "+cl.getCouleur());
        System.out.println("Animal totem: "+cl.getAnimalTotem());
        client(cl);
    }
    public static void mediums(Client cl) {
        ClientService cs = new ClientService();
        List<Medium> mediums = cs.getListMedium();
        Long max;
        max=1L;
        for(Medium m:mediums){
            System.out.println("["+m.getId()+"] "+m.getNom());
            System.out.println("Bio: "+m.getBio());
            if(m instanceof Tarologue){
                System.out.println("Talent: "+((Tarologue) m).getTalent());
                System.out.println("Cartes: "+((Tarologue) m).getCartes());
            }
            else if(m instanceof Voyant){
                System.out.println("Talent: "+((Voyant) m).getTalent());
                System.out.println("Support: "+((Voyant) m).getSupport());
            }
            else if(m instanceof Astrologue){
                System.out.println("Talent: "+((Astrologue) m).getTalent());
                System.out.println("Ecole: "+((Astrologue) m).getEcole());
                System.out.println("Promotion: "+((Astrologue) m).getPromotion());
            }
            if(m.getId()>max){
                max= m.getId();
            }
        }
        System.out.println(max.intValue());
        System.out.println("Veuillez choisir medium");
        int entree = lireInteger("Votre choix: ");
        cs.demanderVoyance(cl, Long.valueOf(entree));
        client(cl);
    }
}