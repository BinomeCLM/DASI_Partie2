/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.insalyon.b3427.positif.dao.JpaUtil;
import fr.insalyon.b3427.positif.modele.Client;
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
        PrintWriter out = response.getWriter();
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

                boolean success = (boolean) request.getAttribute("success");

                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                out.println(success);
                out.close();
                break;

            case "Connexion":
                // Au moment de la connexion qu'on crée la session parce-que au moment de l'inscription on doit 
                // quand même se connecter ensuite
                
                ActionConnexion ac = new ActionConnexion();
                try {
                    ac.executeAction(request);
                } catch (ParseException ex) {
                    Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                Client client = (Client) request.getAttribute("client");
                
                if (client != null){
                    session.setAttribute("idClient", client.getId());
                }
                
                String dataClient = datajson.recupererDataClient(client);
                
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                out.println(dataClient);
                out.close();
                break;

            case "RecupererInfoClient":
                if (session.getAttribute("idClient")!=null){
                    ActionRecupInfoClient aric = new ActionRecupInfoClient();
                    // pour rendre plus robuste vérifier qu'on est connecté if session.getid != null
                    // Coté front des paramètre en ajax que quand il y a de la saisie

                    try {
                        aric.executeAction(request);
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // Faire tout sa dans la classe de formatage (passer en parametre request et response
                    Client clientInfo = (Client) request.getAttribute("client");
                    String dataClientInfo =  datajson.recupererDataClient(clientInfo);

                    response.setContentType("application/json"); // Mettre application/json même si c'est du string
                    // Dans tout le cas c'est du texte, mais c'est pour dire le format du texte retourné
                    response.setCharacterEncoding("UTF-8");
                    out.println(dataClientInfo);
                    out.close();
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
        JpaUtil.destroy();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
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