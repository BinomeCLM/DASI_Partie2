/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.modele.Employe;
import fr.insalyon.b3427.positif.service.EmployeService;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Chris
 */
public class ActionConnexionEmploye extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        Long idEmp = Long.parseLong(request.getParameter("idEmp")) ; 
        System.out.println(idEmp);
        
        EmployeService empServ = new EmployeService();
        Employe emp = empServ.getEmploye(idEmp);
        
        request.setAttribute("employe", emp);
    }
    
}
