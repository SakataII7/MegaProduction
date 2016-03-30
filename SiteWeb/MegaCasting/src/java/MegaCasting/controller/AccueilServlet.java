/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MegaCasting.controller;

import MegaCasting.BDD.ConnexionBDD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ikiuchi
 */
@WebServlet(name = "AccueilServlet", urlPatterns = {"/AccueilServlet"})
public class AccueilServlet extends HttpServlet {

 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection cnx = ConnexionBDD.OpenDB();

        req.setCharacterEncoding("UTF-8");

        Statement stmt = null;

        try {
            
            List<String> NomOffre = new ArrayList<>();
            List<Long> IDoffre = new ArrayList<>();
            List<String> PublicationOffre = new ArrayList<>();
            List<Long> DebutContrat = new ArrayList<>();
            List<String> NombresPostes = new ArrayList<>();
            
            stmt = cnx.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT Identifiant, Intitule, DatePublication, DateDebutContrat, NbPostes FROM Offre");
            
            while(rs.next())
            {                
                NomOffre.add( rs.getString("Intitule"));
                IDoffre.add(rs.getLong("Identifiant"));
                PublicationOffre.add( rs.getString("DatePublication"));
                DebutContrat.add(rs.getLong("DateDebutContrat")); 
                NombresPostes.add( rs.getString("NbPostes"));
            }
            
                HttpSession session = req.getSession();
            
                req.setAttribute("nomoffre",NomOffre);
                req.setAttribute("idoffre",IDoffre);
                req.setAttribute("publicationoffre",PublicationOffre);
                req.setAttribute("debutcontrat",DebutContrat);
                req.setAttribute("nbpostes",NombresPostes);

        } catch (SQLException ex) {
            System.out.println("Une erreur est survenue" + ex);
        }
    }
    
    public static void CloseDB(Connection cnx) //Fermeture
    {        
        //Déconnexion de la base de données
        if(cnx != null)
        {
            try 
            {
                cnx.close();
            } catch (SQLException ex){

            }
        }
    }
}