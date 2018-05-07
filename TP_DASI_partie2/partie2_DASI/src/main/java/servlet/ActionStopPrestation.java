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
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); 
        HttpSession session = request.getSession();
        Long idPrest =  (Long) (session.getAttribute("idPrestation")); 
        Long idEmp =  (Long) (session.getAttribute("idEmploye")); 
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB2"+idPrest);         
        EmployeService empServ = new EmployeService();
        PrestationDAO prestationDAO = new PrestationDAO();
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB3"); 
        //le prestationDAO arrive pas à trouver la prestation. Je voit pas pq. Il faut le faire.
        Prestation p=null;
      
        Employe emp= empServ.getEmploye(idEmp);
        p = empServ.getWaitingPrestation(emp); 
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB4"); 
        
        empServ.stopPrestation(p);   
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB5"); 
        request.setAttribute("success", true);
    }
}
