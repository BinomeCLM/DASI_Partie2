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
        Long idEmp = (Long) session.getAttribute("idEmp");
        
        EmployeService empServ = new EmployeService();
        Employe emp = empServ.getEmploye(idEmp);
        Prestation prestation = empServ.getWaitingPrestation(emp);
        Client cl = null;
        
        if (prestation != null){
            cl = prestation.getClient();
        }
        
        List<Prestation> listePrestation = empServ.getHistoric(cl);
        
        request.setAttribute("listePrestation", listePrestation);
    }
    
}
