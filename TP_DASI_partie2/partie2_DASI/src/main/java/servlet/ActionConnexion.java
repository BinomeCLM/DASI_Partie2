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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cetienne
 */
public class ActionConnexion extends Action {
    
    @Override
    public void executeAction (HttpServletRequest request)
            throws ServletException, IOException, ParseException {
        
        String courriel = request.getParameter("courriel");
        
        ClientService clServ = new ClientService();
        Client cl = clServ.connexion(courriel);
        System.out.println(cl);
        request.setAttribute("client", cl);
    }
}
