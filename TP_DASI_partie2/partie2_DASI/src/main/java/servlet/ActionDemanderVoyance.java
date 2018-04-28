/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.service.ClientService;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chris
 */
class ActionDemanderVoyance extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("idClient");
        
        Long idMedium = Long.parseLong(request.getParameter("id"));
        
        ClientService clServ = new ClientService();
        Client cl = clServ.getClient(id);
        
        boolean etatDemande = clServ.demanderVoyance(cl, idMedium);
        
        System.out.println(etatDemande);
        request.setAttribute("etatDemande", etatDemande);
    }
    
}
