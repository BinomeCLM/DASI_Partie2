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
        System.out.println("id :" + idEmp);
        EmployeService empServ = new EmployeService();
        
        Employe emp = empServ.getEmploye(idEmp);
        System.out.println ("emp " + emp);
        //Prestation p = empServ.getWaitingPrestation(emp);
        System.out.println("prestation  " + laPresta);
        if (laPresta!=null){
            empServ.stopPrestation(laPresta);
            System.out.println("laPresta terminé");
            //Prestation pFinish = empServ.getWaitingPrestation(emp);
            /*if (pFinish == null){
                request.setAttribute("success", true);
                System.out.println(true);
            }
            else {
                request.setAttribute("success", false);
                System.out.println(false);
            }*/
            request.setAttribute("success", true);
            System.out.println(true);
        }
        else 
        {
            request.setAttribute("success", false);
        }
        // On vérifie en essayant de récupérer la prestation "en cours" de nouveau 
        // si celle-ci est nulle c'est que la prestation a bien était stoppé
        // sinon c'est effectivement qu'il y a un problème avec leur code
        
        
        
        /*
        // A changer ou voir psk la on a pas le droit d'utiliser DAO
        PrestationDAO prestationDAO = new PrestationDAO();
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB3"); 
        
        //le prestationDAO arrive pas à trouver la prestation. Je voit pas pq. Il faut le faire.
        
        // J'ai eu le meme probleme Mustafa, je pense qu'on verra tous ensemble comment on
        // fait et on demandera au prof au pire
        Prestation p=null;
      
        Employe emp= empServ.getEmploye(idEmp);
        p = empServ.getWaitingPrestation(emp); 
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB4"); 
        
        empServ.stopPrestation(p);   
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB5"); 
        request.setAttribute("success", true);*/
    }
}
