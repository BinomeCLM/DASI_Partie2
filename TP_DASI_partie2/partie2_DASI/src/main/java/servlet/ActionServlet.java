/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.dao.JpaUtil;
import fr.insalyon.b3427.positif.modele.Client;
import fr.insalyon.b3427.positif.modele.Employe;
import fr.insalyon.b3427.positif.modele.Medium;
import fr.insalyon.b3427.positif.modele.Prestation;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cetienne
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    
    // surcharger une methode init et une methode destroy
    // c'est implementé, pas besoin de s'en charger
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
        String todo = request.getParameter("action");
        if(todo==null)todo="default";
        
        DataJson datajson = new DataJson();
        HttpSession session = request.getSession(true); // Ici on crée une session même s'il n'est pas connecté
        // C'est une solution qui marche mais pas forcément la plus efficace (dépend de l'application de notre site)
        
        switch (todo) {
            
            case "ConfirmationInscription":
                ActionInscription ai = new ActionInscription();
                System.out.println("test1)");
                try {
                    ai.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                datajson.sendInscriEtat(request, response);
                
                break;

            case "Connexion":
                // Au moment de la connexion qu'on crée la session parce-que au moment de l'inscription on doit 
                // quand même se connecter ensuite
                System.out.println("Case Connexion");
                ActionConnexion ac = new ActionConnexion();
                try {
                    ac.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // A voir si on peut ameliorer car dans DataJson je fais aussi un request.getAttribute pareil
                Client cl = (Client) request.getAttribute("client");
                if (cl != null){
                    session.setAttribute("idClient", cl.getId());
                }
                
                datajson.sendEtatConnexion(request,response);
                
                break;

            case "RecupererInfoClient":
                if (session.getAttribute("idClient") != null){
                    ActionRecupInfoClient aric = new ActionRecupInfoClient();
                    // pour rendre plus robuste vérifier qu'on est connecté if session.getid != null
                    // Coté front des paramètre en ajax que quand il y a de la saisie

                    try {
                        aric.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Faire tout sa dans la classe de formatage (passer en parametre request et response
                    datajson.sendDataClient(request, response);
                }
                break;
             
            case "RecupererInfoClientPourEmp" :
                ActionRecupInfoClientPourEmp aricpe = new ActionRecupInfoClientPourEmp();
                try {
                    aricpe.executeAction(request);
                } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                Client c = (Client)request.getAttribute("client");
              
                if(c!=null)
                {
                    session.setAttribute("idClient",c.getId());
                    
                }
                datajson.sendDataClient(request, response);
                break;
            case "RecupererInfoClientPourConsultation" :
                ActionRecupInfoClientPourConsultation aricpc = new ActionRecupInfoClientPourConsultation();
                try {
                    aricpc.executeAction(request);
                } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex); 
                }
                
                
                Client client = (Client)request.getAttribute("client");
              
                datajson.sendDataClient(request, response);
                break;
            case "RecupererInfoEmp" :
                ActionRecupInfosEmp arie = new  ActionRecupInfosEmp();
                try {
                    arie.executeAction(request);
             
                } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Employe emp = (Employe)request.getAttribute("employe");
                
                if(emp != null)
                {
                    session.setAttribute("idEmploye", emp.getId());
                    
                }
                datajson.sendDataEmploye(request, response);
                break;
            case "RecupererInfoPrestation" :
                
                ActionRecupInfoPrestation arip = new ActionRecupInfoPrestation();
                try {
                    arip.executeAction(request);
             
                } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                Prestation pr = (Prestation)request.getAttribute("prestation");
                
                if(pr!=null)
                {
                    session.setAttribute("idPrestation",pr.getId());
                    
                }
                datajson.sendDataPrestation(request, response);
                break;
                
            case "RecupererInfoMedium" :
                ActionRecupInfoMedium arim = new ActionRecupInfoMedium();
                try {
                    arim.executeAction(request);
             
                } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Medium m = (Medium)request.getAttribute("medium");
                
                if(m!=null)
                {
                    session.setAttribute("idMedium",m.getId());
                    
                }
                datajson.sendDataMedium(request, response);
                break;
                
            case "RecupererListeMediums":
                if (session.getAttribute("idClient") != null) {
                    ActionRecupListeMed arlm = new ActionRecupListeMed();
                    
                    try {
                        arlm.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    datajson.sendListeMed(request, response);
                }
                break;
                
            case "RecupererListePrestations":
                System.out.println("RecupererListePrestationsXXXXXXXXxx");
                ActionRecupListePrest arlp = new ActionRecupListePrest();

                try {
                    arlp.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                

                datajson.sendListePrest(request, response);
                break;
                
            case "DemanderVoyance":
                if (session.getAttribute("idClient") != null) {
                    ActionDemanderVoyance adv = new ActionDemanderVoyance();
                    
                    try {
                        adv.executeAction(request);
                    } catch (ParseException ex) { 
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    datajson.sendConfVoyance(request, response);
                    
                }
                break;
            
            case "StartPrestation"  :
                ActionStartPrestation asp = new ActionStartPrestation();
                try {
                    asp.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Prestation p = (Prestation)request.getAttribute("prestation");
                
                if(p!=null)
                {
                    session.setAttribute("idPrestation",p.getId());
                    
                }
                datajson.sendDataPrestation(request, response);
                break;

            case "StopPrestation"  :
                System.out.println("ABCABCACBABCSAASFJAKFAJLKFSAFSAFS"); 
                ActionStopPrestation astopp = new ActionStopPrestation(); 
                try {
                    astopp.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("ABCABCACBABCSAASFJAKFAJLKFSAFSAFS2"); 
                
                datajson.sendInscriStopPrest(request, response);
                break;
                
                
            default:
                System.out.println("erreurAction");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.destroy();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
