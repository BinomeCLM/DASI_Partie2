/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Employe;
import fr.insalyon.b3427.positif.modele.Medium;
import fr.insalyon.b3427.positif.modele.Prestation;
import fr.insalyon.b3427.positif.service.ClientService;
import fr.insalyon.b3427.positif.service.EmployeService;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mustafa
 */
public class ActionRecupListePrest extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        HttpSession session = request.getSession();
        // Pour moi on récupére pas la session du client mais celle 
        // de l'employé vu qu'on est dans EmployeService et a partir de la
        // on récupére les infos des clients 
        // Long idCl = (Long) session.getAttribute("idClient");
        Long idEmp = (Long) session.getAttribute("idEmp");
        
        EmployeService empServ = new EmployeService();
        System.out.println(idEmp + "coucou");
        // On récupére d'abord l'employé (celui qui est connecté) grâce à la session en cours
        Employe emp = empServ.getEmploye(idEmp);
        // Ensuite on récupére la prestation qu'il doit traiter
        Prestation prestation = empServ.getWaitingPrestation(emp);
        // A partir de la prestation, on récupére le client qui la demande
        Client cl = null;
        System.out.println("prestation liste : " + prestation);
        if (prestation != null){
            cl = prestation.getClient();
        }
        // Une fois toutes les étapes précédentes réalisées, on récupère
        // l'historique du client DEMANDANT LA PRESTATION (ATTENTION à ne pas confondre avec les IHM côté client
        // car la ça marchait mais quand nous aurons implémenté la deconnexion, ça ne marchera plus !!
        List<Prestation> listePrestation = empServ.getHistoric(cl);
        
        request.setAttribute("listePrestation", listePrestation);
    }
    
}
