/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Employe;
import fr.insalyon.b3427.positif.modele.Prestation;
import fr.insalyon.b3427.positif.service.EmployeService;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mustafa
 */
public class ActionRecupInfoClientPourConsultation extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        HttpSession session = request.getSession();
        // Attention je pense qu'il faut recuperer l'employe
        // puis le client dont l'employe est en charge, tu valides ?
        // J'ai mis en commentaire ton code
        // J'ai rajouté dans startPrestation l'action, le client dans la session
        
        //Long idClient = (Long) session.getAttribute("idClient"); 
        Client cl = (Client) session.getAttribute("clientPresta");
        
        EmployeService empServ = new EmployeService();
        // Client cl = empServ.getClient(idClient); 
        
        request.setAttribute("client",cl);
    }
    
}