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
        String todo = request.getParameter("action");
        
        DataJson datajson = new DataJson();
        HttpSession session = request.getSession(true); 
        
        switch (todo) {
            
            case "ConfirmationInscription":
                ActionInscription ai = new ActionInscription();
                try {
                    ai.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                datajson.sendInscriEtat(request, response);
                
                break;
                
            case "ConnexionClient":
                ActionConnexion ac = new ActionConnexion();
                try {
                    ac.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Client cl = (Client) request.getAttribute("client");
                if (cl != null){
                    session.setAttribute("idClient", cl.getId());
                }
                
                datajson.sendEtatConnexion(request,response);
                
                break;
                
            case "ConnexionEmploye":
                
                ActionConnexionEmploye ace = new ActionConnexionEmploye();
                try {
                    ace.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Employe emp = (Employe) request.getAttribute("employe");
                if (emp != null){
                    session.setAttribute("idEmp", emp.getId());
                }
                
                datajson.sendEtatConnexionEmploye(request,response);
                
                break;
                
            case "RecupererInfoClient":
                
                if (session.getAttribute("idClient") != null){
                    ActionRecupInfoClient aric = new ActionRecupInfoClient();

                    try {
                        aric.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    datajson.sendDataClient(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
             
            case "RecupererInfoClientPourEmp" :
                if (session.getAttribute("idEmp") != null){
                    ActionRecupInfoClientPourEmp aricpe = new ActionRecupInfoClientPourEmp();
                    try {
                        aricpe.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Client leClientDeEmp = (Client) request.getAttribute("client");
                    Client clientPresta = (Client) session.getAttribute("clientPresta");
                    if (leClientDeEmp != null && clientPresta == null){
                        session.setAttribute("clientPresta", leClientDeEmp);
                    }
                    
                    datajson.sendDataClient(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
                
            case "RecupererInfoClientPourConsultation" :
                
                if (session.getAttribute("idEmp") != null){
                    ActionRecupInfoClientPourConsultation aricpc = new ActionRecupInfoClientPourConsultation();
                    try {
                        aricpc.executeAction(request);
                    } catch (ParseException ex) {
                            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex); 
                    }
                    
                    datajson.sendDataClient(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
                
            case "RecupererInfoEmp" :
                if (session.getAttribute("idEmp") != null){
                    ActionRecupInfosEmp arie = new  ActionRecupInfosEmp();
                    try {
                        arie.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    datajson.sendDataEmploye(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
                
            case "RecupererInfoPrestation" :
                if (session.getAttribute("idEmp") != null){
                    ActionRecupInfoPrestation arip = new ActionRecupInfoPrestation();
                    try {
                        arip.executeAction(request);

                    } catch (ParseException ex) {
                            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                    datajson.sendDataPrestation(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
                
            case "RecupererInfoMedium" :
                
                if (session.getAttribute("idEmp") != null){
                    ActionRecupInfoMedium arim = new ActionRecupInfoMedium();
                    try {
                        arim.executeAction(request);

                    } catch (ParseException ex) {
                            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    datajson.sendDataMedium(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
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
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
                
            case "RecupererListePrestations":
                
                if (session.getAttribute("idEmp") != null){

                    ActionRecupListePrest arlp = new ActionRecupListePrest();
                    try {
                        arlp.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    datajson.sendListePrest(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
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
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
            
            case "StartPrestation"  :
                
                if (session.getAttribute("idEmp") != null){
                    ActionStartPrestation asp = new ActionStartPrestation();
                    try {
                        asp.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Prestation p = (Prestation)request.getAttribute("prestation");
                    
                    if(p!=null)
                    {
                        session.setAttribute("prestation",p);
                    }
                    
                    datajson.sendDataPrestation(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;

            case "StopPrestation":
                
                if (session.getAttribute("prestation") != null){
                    ActionStopPrestation astopp = new ActionStopPrestation(); 
                    try {
                        astopp.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if ((boolean) request.getAttribute("success")){
                        session.removeAttribute("prestation");
                        session.removeAttribute("clientPresta");
                    }
                    
                    datajson.sendInscriStopPrest(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
                
            case "GenererPrediction":
                
                if (session.getAttribute("idEmp") != null && session.getAttribute("clientPresta") != null){
                    ActionGenererPrediction agp = new ActionGenererPrediction(); 
                    agp.executeAction(request);

                    session.setAttribute("prediction", request.getAttribute("laPrediction"));
                    session.setAttribute("valeuramour", (int) request.getAttribute("valeuramour"));
                    session.setAttribute("valeursante", (int) request.getAttribute("valeursante"));
                    session.setAttribute("valeurtravail", (int) request.getAttribute("valeurtravail"));
                      
                    request.setAttribute("etatPred", true);
                }
                else 
                {
                    request.setAttribute("etatPred", false);
                }
                
                datajson.sendConfGenerationPred(request, response);
                
                break;
            
            case "ObtenirPrediction":
                
                if (session.getAttribute("idEmp") != null){
                    ActionObtenirPrediction aop = new ActionObtenirPrediction(); 
                    try {
                        aop.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    datajson.sendPrediction(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
                
            case "ObtenirListeEmploye":
                
                if (session.getAttribute("idEmp") != null){
                    ActionObtenirListeEmp aole = new ActionObtenirListeEmp(); 
                    try {
                        aole.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    datajson.sendListeEmploye(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
                
            case "ObtenirListeMediums":
                
                if (session.getAttribute("idEmp") != null) {
                    ActionRecupListeMed arlm = new ActionRecupListeMed();
                    
                    try {
                        arlm.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    datajson.sendListeMed(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
                break;
                
            case "Deconnecter":
                
                if (session.getAttribute("idEmp") != null || session.getAttribute("idClient") != null) {
                    ActionDeconnexion ad = new ActionDeconnexion();
                    
                    try {
                        ad.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    datajson.sendDeconnexionEtat(request, response);
                }
                else {
                    datajson.sendDataRedirection(request, response);
                }
                
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
