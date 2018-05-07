/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Medium;
import fr.insalyon.b3427.positif.service.ClientService;
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
public class ActionRecupListeMed extends Action {

    @Override
    public void executeAction(HttpServletRequest request) throws ServletException, IOException, ParseException {
        
        ClientService clServ = new ClientService();
        
        List<Medium> listeMedium = clServ.getListMedium();
        System.out.println(listeMedium.get(0));
        request.setAttribute("listeMedium", listeMedium);
    }
    
}
