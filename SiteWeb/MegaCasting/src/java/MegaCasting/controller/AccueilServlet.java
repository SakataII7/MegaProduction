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
import java.sql.PreparedStatement;
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
        
        //Récupère les valeurs nécessaires à la fonction de recherche
        String identifiantDomaine = req.getParameter("UneRecherche");
        String recherche = req.getParameter("search");
        

        try {
            
            //Création des listes qui auront les résultats des requêtes SQL
            List<String> NomCategorie = new ArrayList<>();
            List<Long> IdCategorie = new ArrayList<>();
            List<String> NomOffre = new ArrayList<>();
            List<Long> IDoffre = new ArrayList<>();
            List<Date> DebutContrat = new ArrayList<>();
            List<Long> NombresPostes = new ArrayList<>();

            stmt = cnx.createStatement();
            PreparedStatement pstmt = null;
            
            //Lance la bonne requête pour l'affichage des offres
            //recherche quand un élément est mis dans la textbox
            if (recherche != null)
            {
                recherche = '%'+recherche+'%'; //Le % ne fonctionnait pas dans la requête SQL
                //récupère les informations qu'on affiche sur la page d'accueil 
                pstmt = cnx.prepareStatement("SELECT Identifiant, Intitule, DateDebutContrat, NbPostes, IdentifiantDomaineMetier FROM Offre WHERE Intitule LIKE ?");
                pstmt.setString(1, recherche);
                ResultSet rs = pstmt.executeQuery();
                
                while(rs.next())
                {                
                    NomOffre.add( rs.getString("Intitule"));
                    IDoffre.add(rs.getLong("Identifiant"));
                    DebutContrat.add(rs.getDate("DateDebutContrat")); 
                    NombresPostes.add( rs.getLong("NbPostes"));
                }
            }
            //identifiantDomaine quand l'utilisateur clique sur un domaine
            else if(identifiantDomaine != null)
            {
                Long idDomaine = Long.parseLong(identifiantDomaine);
                
                pstmt = cnx.prepareStatement("SELECT Identifiant, Intitule, DateDebutContrat, NbPostes, IdentifiantDomaineMetier FROM Offre WHERE Offre.IdentifiantDomaineMetier = ?");
                pstmt.setLong(1, idDomaine);    
                ResultSet rs = pstmt.executeQuery();
                
                while(rs.next())
                {                
                    NomOffre.add( rs.getString("Intitule"));
                    IDoffre.add(rs.getLong("Identifiant"));
                    DebutContrat.add(rs.getDate("DateDebutContrat")); 
                    NombresPostes.add( rs.getLong("NbPostes"));
                }
            }
            //quand on clique sur l'accueil ou la première fois qu'on arrive sur la page
            else
            {
                ResultSet rs = stmt.executeQuery("SELECT Identifiant, Intitule, DateDebutContrat, NbPostes, IdentifiantDomaineMetier FROM Offre");
                
                while(rs.next())
                {                
                    NomOffre.add( rs.getString("Intitule"));
                    IDoffre.add(rs.getLong("Identifiant"));
                    DebutContrat.add(rs.getDate("DateDebutContrat")); 
                    NombresPostes.add( rs.getLong("NbPostes"));
                }
            }

                req.setAttribute("idoffre", IDoffre);
                req.setAttribute("nomoffre", NomOffre);
                req.setAttribute("debutcontrat", DebutContrat);
                req.setAttribute("nbpostes", NombresPostes);
     
                ResultSet rs2 = stmt.executeQuery("SELECT Identifiant, Libelle FROM DomaineMetier");

            while(rs2.next())
            {                
                NomCategorie.add( rs2.getString("Libelle"));
                IdCategorie.add(rs2.getLong("Identifiant"));
            }
            
                req.setAttribute("idcategorie", IdCategorie);
                req.setAttribute("nomcategorie", NomCategorie);
                
        } catch (SQLException ex) {
            System.out.println("Une erreur est survenue" + ex);
        }
        
        RequestDispatcher rq = req.getRequestDispatcher("Pages/Accueil.jsp");
        rq.forward(req, resp);
        
        ConnexionBDD.CloseDB(cnx);
    }
}