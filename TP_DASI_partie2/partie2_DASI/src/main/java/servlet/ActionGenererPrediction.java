/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Employe;
import fr.insalyon.b3427.positif.service.EmployeService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chris
 */
class ActionGenererPrediction extends Action{

    @Override
    public void executeAction(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("idEmp");
        
        int amour = Integer.parseInt(request.getParameter("amour"));
        int sante = Integer.parseInt(request.getParameter("sante"));
        int travail = Integer.parseInt(request.getParameter("travail"));
        
        
        EmployeService empServ = new EmployeService();
        Employe emp = empServ.getEmploye(id);
        Client cl = empServ.getClient(id);
        List<String> laPrediction = empServ.getPrediction(cl, amour, sante, travail);
        
        request.setAttribute("laPrediction", laPrediction);
        request.setAttribute("valSante", sante);
        request.setAttribute("valAmour", amour);
        request.setAttribute("valTravail", travail);
    }
    
}
