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

/**
 *
 * @author lghandour
 */
public class ActionRecupInfosEmp extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        //HttpSession session = request.getSession();
        //Long id = (Long) session.getAttribute("idClient");
        
        EmployeService empServ = new EmployeService();
        System.out.println(request.getParameter("employe"));
        Long idEmp = 
        idEmp = Long.parseLong(request.getParameter("employe")) ;
        Employe emp = empServ.getEmploye(idEmp);
        Prestation p = empServ.getWaitingPrestation(emp);
        
             
        request.setAttribute("employe",emp);
//        request.setAttribute("client", cl);
    }
}
