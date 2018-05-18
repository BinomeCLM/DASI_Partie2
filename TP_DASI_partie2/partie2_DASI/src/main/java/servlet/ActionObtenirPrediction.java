/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Employe;
import fr.insalyon.b3427.positif.service.EmployeService;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chris
 */
class ActionObtenirPrediction extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        HttpSession session = request.getSession();
        List<String> dataPrediction = (List<String>) session.getAttribute("prediction");
        Long idEmp = (Long) session.getAttribute("idEmp");
        Client cl = (Client) session.getAttribute("clientPresta");
        int amourVal = (int) session.getAttribute("valeuramour");
        int santeVal = (int) session.getAttribute("valeursante");
        int travailVal = (int) session.getAttribute("valeurtravail");
        
        EmployeService empServ = new EmployeService();
        //Client cl = empServ.getClient(id);
        Employe emp = empServ.getEmploye(idEmp);
        
        request.setAttribute("dataPrediction", dataPrediction);
        request.setAttribute("leClientPrediction", cl);
        request.setAttribute("valeuramour", amourVal);
        request.setAttribute("valeursante", santeVal);
        request.setAttribute("valeurtravail", travailVal);
        request.setAttribute("employe", emp);
        
    }
    
}
