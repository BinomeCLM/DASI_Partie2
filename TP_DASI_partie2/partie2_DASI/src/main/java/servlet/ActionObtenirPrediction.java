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
        List<String> dataPrediction = (List<String>) session.getAttribute("laPrediction");
        Long id = (Long) session.getAttribute("idEmp");
        
        int amourVal = (int) session.getAttribute("amourVal");
        int santeVal = (int) session.getAttribute("santeVal");
        int travailVal = (int) session.getAttribute("travailVal");
        
        EmployeService empServ = new EmployeService();
        Client cl = empServ.getClient(id);
        Employe emp = empServ.getEmploye(id);
        
        request.setAttribute("dataPrediction", dataPrediction);
        request.setAttribute("leClientPrediction", cl);
        request.setAttribute("amourVal", amourVal);
        request.setAttribute("santeVal", santeVal);
        request.setAttribute("travailVal", travailVal);
        request.setAttribute("employe", emp);
        
    }
    
}
