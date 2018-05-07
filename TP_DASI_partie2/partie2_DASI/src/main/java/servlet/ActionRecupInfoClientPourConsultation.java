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
        Long idClient = (Long) session.getAttribute("idClient"); 
//        System.out.println("xxxxxxxxxxxxxx"+id);
        EmployeService empServ = new EmployeService();
        
       
        System.out.println("xxxxxxxxxxxxxx2");
        Client cl = empServ.getClient(idClient); 
        request.setAttribute("client",cl);

//        request.setAttribute("client", cl);
    }
    
}