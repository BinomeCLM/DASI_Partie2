/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.b3427.positif.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cetienne
 */
public class DataJson {
    
    public void sendInscriEtat(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        boolean success = (boolean) request.getAttribute("success");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        out.println(success);
        out.close();
    }
    
    public void recupererDataClient(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String data = "";
        
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonClient = new JsonObject();
        
        Client cl = (Client) request.getAttribute("client");
        
        if (cl!=null){
            jsonClient.addProperty("etat", true);
            jsonClient.addProperty("id",cl.getId());
            jsonClient.addProperty("civilite",cl.getCivilite());
            jsonClient.addProperty("nom",cl.getNom());
            jsonClient.addProperty("prenom", cl.getPrenom());
            jsonClient.addProperty("mail", cl.getCourriel());
            jsonClient.addProperty("adresse",cl.getAdressePostale());
            jsonClient.addProperty("signeZodiaque", cl.getSigneZodiaque());
            jsonClient.addProperty("signeChinois", cl.getSigneChinois());
            jsonClient.addProperty("telephone", cl.getTelephone());
            jsonClient.addProperty("couleur", cl.getCouleur());
            jsonClient.addProperty("animalTotem", cl.getAnimalTotem());
        }
        else {
            jsonClient.addProperty("etat", false);
        }
        
        data = gson.toJson(jsonClient);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(data);
        out.close();
        
    }
}
