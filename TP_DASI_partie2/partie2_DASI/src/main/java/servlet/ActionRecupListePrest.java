/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.modele.Client;
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
        Long idCl = (Long) session.getAttribute("idClient");
        System.out.println("XXXXXXXXidCl="+idCl);
        EmployeService empServ = new EmployeService();
        
        Client cl = empServ.getClient(idCl);
        System.out.println("XXXXXXXXcl="+cl);
        List<Prestation> listePrestation = empServ.getHistoric(cl);
        if(listePrestation==null){
            System.out.println("null lan");          
        }else{
            System.out.println("XXXXXXXX"+listePrestation.get(0).getId());          
            System.out.println("null degil");          
        }
        request.setAttribute("listePrestation", listePrestation);
    }
    
}
