/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.dao.PrestationDAO;
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

public class ActionStopPrestation extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        HttpSession session = request.getSession();
        Long idEmp =  (Long) (session.getAttribute("idEmp")); 
        Prestation laPresta = (Prestation) (session.getAttribute("prestation"));
        
        EmployeService empServ = new EmployeService();
        Employe emp = empServ.getEmploye(idEmp);
        
        if (laPresta!=null){
            empServ.stopPrestation(laPresta);
            request.setAttribute("success", true);
        }
        else 
        {
            request.setAttribute("success", false);
        }
    }
}
