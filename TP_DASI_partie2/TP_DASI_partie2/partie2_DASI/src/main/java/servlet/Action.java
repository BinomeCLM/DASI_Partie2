/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cetienne
 */


public abstract class Action {
    // Voir page 37 pour request seulement et module sérialisation des données
    public abstract void executeAction (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException;
}
