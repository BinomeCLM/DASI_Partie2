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
import fr.insalyon.b3427.positif.modele.Employe;
import fr.insalyon.b3427.positif.modele.Medium;
import fr.insalyon.b3427.positif.modele.Prestation;
import fr.insalyon.b3427.positif.modele.Tarologue;
import fr.insalyon.b3427.positif.modele.Voyant;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        
        boolean success = (boolean) request.getAttribute("success");
        jo.addProperty("success", success);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jo));
        out.close();
    }
    
    public void sendInscriStopPrest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        
        boolean success = (boolean) request.getAttribute("success");
        jo.addProperty("success", success);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jo));
        out.close();
    }
    
    public void sendEtatConnexion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        PrintWriter out = response.getWriter();
        
        Client cl = (Client) request.getAttribute("client");
        boolean etatConnexion = false;
        if (cl != null) {
            etatConnexion = true;
        }
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        
        jo.addProperty("success", etatConnexion);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(gson.toJson(jo));
        
        out.close();
    }
    
    public void sendDataRedirection(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonClient = new JsonObject();
        
        jsonClient.addProperty("id", -1);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jsonClient));
        
        out.close();
    }
    
    public void sendDataClient(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonClient = new JsonObject();
        
        Client cl = (Client) request.getAttribute("client");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        if (cl!=null){
            jsonClient.addProperty("id",cl.getId());
            jsonClient.addProperty("civilite",cl.getCivilite());
            jsonClient.addProperty("nom",cl.getNom());
            jsonClient.addProperty("prenom", cl.getPrenom());
            jsonClient.addProperty("mail", cl.getCourriel());
            Date d = cl.getDateNaissance();
            String dateDeNaissance=sdf.format(d);
            jsonClient.addProperty("dateDeNaissance", dateDeNaissance); 
            jsonClient.addProperty("adresse",cl.getAdressePostale());
            jsonClient.addProperty("signeZodiaque", cl.getSigneZodiaque());
            jsonClient.addProperty("signeChinois", cl.getSigneChinois());
            jsonClient.addProperty("telephone", cl.getTelephone());
            jsonClient.addProperty("couleur", cl.getCouleur());
            jsonClient.addProperty("animalTotem", cl.getAnimalTotem());
        }
        else {
            jsonClient.addProperty("id", -1);
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jsonClient));
        out.close();
        
    }
    
    public void sendDataEmploye(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonEmploye = new JsonObject();
        
        Employe emp = (Employe)request.getAttribute("employe");
        
        if (emp!=null){
            jsonEmploye.addProperty("id",emp.getId());
            jsonEmploye.addProperty("nom",emp.getNomEmploye());
            jsonEmploye.addProperty("talent", emp.getTalent());
            jsonEmploye.addProperty("nbPrestation", emp.getNbPrestation());
            jsonEmploye.addProperty("busy", emp.isBusy());
        }
        else {
            jsonEmploye.addProperty("etat", false);
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jsonEmploye));
        out.close();
    }
    
    public void sendDataPrestation(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonPrestation = new JsonObject();
        
        Prestation pr = (Prestation) request.getAttribute("prestation");
        
        if (pr!=null){
            jsonPrestation.addProperty("id",pr.getId());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            jsonPrestation.addProperty("heureDebut",sdf.format(pr.getHeureDebut()));
            
            if(pr.getHeureFin() != null){
                jsonPrestation.addProperty("heureFin", sdf.format(pr.getHeureFin()));
            }
            
            jsonPrestation.addProperty("employe", (pr.getEmploye()).getId());
            jsonPrestation.addProperty("client", (pr.getClient()).getId());
            jsonPrestation.addProperty("medium", (pr.getMedium()).getId());
        }
        else {
            jsonPrestation.addProperty("client", -1);
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jsonPrestation));
        out.close();
    }
    
    public void sendDataMedium(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonMedium = new JsonObject();
        
        Medium m = (Medium) request.getAttribute("medium");
        
        if (m!=null){
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
        } else {
            jsonMedium.addProperty("id", -1);
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jsonMedium));
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
            jsonMedium.addProperty("nbPresta", m.getNbPrestation());
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

    public void sendListePrest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject container = new JsonObject();
        JsonArray jsonListe = new JsonArray();
        
        List<Prestation> listePrestation = (List<Prestation>) request.getAttribute("listePrestation");

        for (Prestation p : listePrestation) {
            if (p.getHeureFin() != null){
                JsonObject jsonPrestation = new JsonObject();

                jsonPrestation.addProperty("id",p.getId());
                jsonPrestation.addProperty("idClient",p.getClient().getId());
                jsonPrestation.addProperty("idEmploye",p.getEmploye().getId());
                jsonPrestation.addProperty("idMedium",p.getMedium().getId());
                jsonPrestation.addProperty("mediumStr",p.getMedium().getNom()+" ("+p.getMedium().getTalent()+")");
                jsonPrestation.addProperty("employeStr",p.getEmploye().getNomEmploye()); 

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

                jsonPrestation.addProperty("heureDebut", sdf.format(p.getHeureDebut()));
                jsonPrestation.addProperty("heureFin", sdf.format(p.getHeureFin()));

                Long diff = p.getHeureFin().getTime()-p.getHeureDebut().getTime();
                Long diffSeconds = diff / 1000 % 60;         
                Long diffMinutes = diff / (60 * 1000) % 60;         
                Long diffHours = diff / (60 * 60 * 1000);
                String duree= diffHours+"'"+diffMinutes+"''"+diffSeconds+"'''";

                jsonPrestation.addProperty("dureeStr",duree); 
            
                jsonListe.add(jsonPrestation);
            }
        }
    
        container.add("prestations", jsonListe);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(container));
        out.close();
    }

    void sendConfVoyance(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        
        boolean etatDemande = (boolean) request.getAttribute("etatDemande");
        jo.addProperty("success", etatDemande);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jo));
        out.close();
    }

    void sendEtatConnexionEmploye(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        PrintWriter out = response.getWriter();
        
        Employe emp = (Employe) request.getAttribute("employe");
        boolean etatConnexion = false;
        if (emp != null) {
            etatConnexion = true;
        }
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        
        jo.addProperty("success", etatConnexion);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(gson.toJson(jo));
        
        out.close();
    }

    void sendConfGenerationPred(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        
        boolean etatPrediction = (boolean) request.getAttribute("etatPred");
        jo.addProperty("success", etatPrediction);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jo));
        out.close();
    }

    void sendPrediction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonPrediction = new JsonObject();
        
        List<String> donnees = (List<String>) request.getAttribute("dataPrediction");
        int valAmour = (int) request.getAttribute("valeuramour");
        int valSante = (int) request.getAttribute("valeursante");
        int valTravail = (int) request.getAttribute("valeurtravail");
        Client client = (Client) request.getAttribute("leClientPrediction");
        Employe emp = (Employe) request.getAttribute("employe");
        System.out.println(client + " pour prediction");
        System.out.println(emp + " pour prediction");
        System.out.println(valSante + " pour prediction");
        System.out.println(donnees.get(0) + " pour prediction");
        if (donnees != null){
            jsonPrediction.addProperty("amourStr",donnees.get(0));
            jsonPrediction.addProperty("santeStr", donnees.get(1));
            jsonPrediction.addProperty("travailStr", donnees.get(2));
            jsonPrediction.addProperty("amourVal", valAmour);
            jsonPrediction.addProperty("santeVal", valSante);
            jsonPrediction.addProperty("travailVal", valTravail);
            jsonPrediction.addProperty("prenomClient", client.getPrenom());
            jsonPrediction.addProperty("idClient", client.getId());
            jsonPrediction.addProperty("nomClient", client.getNom());
            jsonPrediction.addProperty("nomEmploye", emp.getNomEmploye());
        } else {
            jsonPrediction.addProperty("idClient", -1);
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jsonPrediction));
        out.close();
    }

    void sendListeEmploye(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject container = new JsonObject();
        JsonArray jsonListe = new JsonArray();
        
        List<Employe> listeEmploye = (List<Employe>) request.getAttribute("listeEmp");

        for (Employe e : listeEmploye) {
                JsonObject jsonEmploye = new JsonObject();

                jsonEmploye.addProperty("id",e.getId());
                jsonEmploye.addProperty("nom",e.getNomEmploye());
                jsonEmploye.addProperty("nbPresta",e.getNbPrestation()); 
            
                jsonListe.add(jsonEmploye);
        }
    
        container.add("employes", jsonListe);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(container));
        out.close();
    }

    void sendDeconnexionEtat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        
        jo.addProperty("deconnexion", 1);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(gson.toJson(jo));
        out.close();
    }
}
