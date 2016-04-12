/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MegaCasting.controller;

import MegaCasting.BDD.ConnexionBDD;
import MegaCasting.classes.Offre;
import MegaCasting.classes.ViewOffre;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ikiuchi
 */
@WebServlet(name = "AccueilServlet", urlPatterns = {"/accueil"})
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
            List<Date> DebutContrat = new ArrayList<>();
            List<Long> NombresPostes = new ArrayList<>();

            stmt = cnx.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT Identifiant, Intitule, DateDebutContrat, NbPostes FROM Offre");

            while(rs.next())
            {                
                NomOffre.add( rs.getString("Intitule"));
                IDoffre.add(rs.getLong("Identifiant"));
                DebutContrat.add(rs.getDate("DateDebutContrat")); 
                NombresPostes.add( rs.getLong("NbPostes"));
            }
            
                req.setAttribute("idoffre", IDoffre);
                req.setAttribute("nomoffre", NomOffre);
                req.setAttribute("debutcontrat", DebutContrat);
                req.setAttribute("nbpostes", NombresPostes);

        } catch (SQLException ex) {
            System.out.println("Une erreur est survenue" + ex);
        }
        
        RequestDispatcher rq = req.getRequestDispatcher("Pages/Accueil.jsp");
        rq.forward(req, resp);
        
        ConnexionBDD.CloseDB(cnx);
    }
}