/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Prestation;
import fr.insalyon.b3427.positif.service.ClientService;
import fr.insalyon.b3427.positif.service.EmployeService;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chris
 */
class ActionRecupInfoMedium extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        EmployeService empServ = new EmployeService();
        Prestation prestation = empServ.getWaitingPrestation(emp);
        System.out.println(prestation);
        request.setAttribute("prestation", prestation);
    }
    
}
