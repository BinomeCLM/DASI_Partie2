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
import fr.insalyon.b3427.positif.modele.Astrologue;
import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Medium;
import fr.insalyon.b3427.positif.modele.Tarologue;
import fr.insalyon.b3427.positif.modele.Voyant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cetienne
 */
public class DataJson {
    
    public void sendInscriEtat(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        PrintWriter out = response.getWriter();
        
        boolean success = (boolean) request.getAttribute("success");
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        out.println(success);
        out.close();
    }
    
    public void sendEtatConnexion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        PrintWriter out = response.getWriter();
        
        Client cl = (Client) request.getAttribute("client");
        boolean etatConnexion = false;
        if (cl != null) {
            etatConnexion = true;
        }
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        out.println(etatConnexion);
        out.close();
    }
    
    public void sendDataClient(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonClient = new JsonObject();
        
        Client cl = (Client) request.getAttribute("client");
        
        if (cl!=null){
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
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jsonClient));
        out.close();
        
    }

    public void sendListeMed(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject container = new JsonObject();
        JsonArray jsonListe = new JsonArray();
        
        List<Medium> listeMedium = (List<Medium>) request.getAttribute("listeMedium");
        
        for (Medium m : listeMedium) {
            JsonObject jsonMedium = new JsonObject();
            
            jsonMedium.addProperty("nom",m.getNom());
            jsonMedium.addProperty("talent",m.getTalent());
            jsonMedium.addProperty("bio",m.getBio());
            jsonMedium.addProperty("metier", m.getClass().getSimpleName());
            jsonMedium.addProperty("id", m.getId());
            
            String classeFille = m.getClass().getSimpleName();
            if (classeFille.equals("Tarologue")) {
                jsonMedium.addProperty("cartes", ((Tarologue)m).getCartes());
            }
            else if (classeFille.equals("Astrologue")) {
                jsonMedium.addProperty("ecole", ((Astrologue)m).getEcole());
                jsonMedium.addProperty("promotion", ((Astrologue)m).getPromotion());
            }
            else {
                jsonMedium.addProperty("support", ((Voyant)m).getSupport());
            }
            
            jsonListe.add(jsonMedium);
        }
    
        container.add("mediums", jsonListe);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(container));
        out.close();
    }

    void sendConfVoyance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        PrintWriter out = response.getWriter();
        
        boolean etatDemande = (boolean) request.getAttribute("etatDemande");
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        out.println(etatDemande);
        out.close();
    }
}
