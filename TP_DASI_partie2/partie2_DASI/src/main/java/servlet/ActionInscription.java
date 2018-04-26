/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.service.ClientService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cetienne
 */
public class ActionInscription extends Action {

    @Override
    public void executeAction (HttpServletRequest request)
            throws ServletException, IOException, ParseException {
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String civilite = request.getParameter("civilite");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        String mail = request.getParameter("mail");
        String dateNaissanceStr = request.getParameter("dateNaissance");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date dateNaissance = sdf.parse(dateNaissanceStr);
        
        Client cl = new Client(civilite,prenom,nom,dateNaissance,adresse,telephone,mail);
        ClientService clServ = new ClientService();
        boolean success = clServ.inscription(cl);
        
        request.setAttribute("success", success);
        
    }

}
