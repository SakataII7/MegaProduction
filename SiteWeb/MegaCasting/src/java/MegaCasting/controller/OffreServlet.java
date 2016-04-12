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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "OffreServlet", urlPatterns = {"/offre"})
public class OffreServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
       
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        
        Connection cnx = ConnexionBDD.OpenDB();
        
        //On récupère l'id de l'Offre
        String identifiant = req.getParameter("UneOffre");
        Long idOffre = Long.parseLong(identifiant);
        
        PreparedStatement pstmt = null;
        
        try {
            
            String intitule = null;
            String reference = null;
            Date datePublication = null;
            Date dateDebutContrat = null;
            Long dureeDiffusion = null;
            Long nbPostes = null;
            String descriptionPoste = null;
            String descriptionProfil = null;
            String telephone = null;
            String email = null;
            String TypeContrat = null;
            String Metier = null;
            String DomaineMetier = null;
            String Client = null;
            
            //On cherche les produits de la catégorie dont on a récupéré l'id
            pstmt = cnx.prepareStatement("SELECT Intitule, Reference, DatePublication, DateDebutContrat, DureeDiffusion, NbPostes, DescriptionPoste, DescriptionProfil, Offre.Telephone, Offre.Email, Metier.Libelle AS Metier, DomaineMetier.Libelle AS Domaine, TypeContrat.Libelle AS Type, Client.Libelle AS Client FROM Offre LEFT JOIN Metier ON IdentifiantMetier = Metier.Identifiant LEFT JOIN DomaineMetier ON Metier.IdentifiantDomaineMetier = DomaineMetier.Identifiant LEFT JOIN Client ON Offre.IdentifiantClient = Client.Identifiant LEFT JOIN TypeContrat ON Offre.IdentifiantTypeContrat = TypeContrat.Identifiant WHERE Offre.Identifiant = ?"); 
            pstmt.setLong(1, idOffre);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                intitule =  rs.getString("Intitule");
                reference =  rs.getString("Reference");
                datePublication =  rs.getDate("DatePublication");
                dateDebutContrat =  rs.getDate("DateDebutContrat");
                dureeDiffusion =  rs.getLong("DureeDiffusion");
                nbPostes =  rs.getLong("NbPostes");
                descriptionPoste =  rs.getString("DescriptionPoste");
                descriptionProfil =  rs.getString("DescriptionProfil");
                telephone =  rs.getString("Telephone");
                email =  rs.getString("Email");
                TypeContrat =  rs.getString("Type");
                Metier =  rs.getString("Metier");
                DomaineMetier =  rs.getString("Domaine");
                Client =  rs.getString("Client");
            }
            
                req.setAttribute("idoffre",idOffre);
                req.setAttribute("nomoffre",intitule);
                req.setAttribute("ref",reference);
                req.setAttribute("publication",datePublication);
                req.setAttribute("debutcontrat",dateDebutContrat);
                req.setAttribute("dureediff",dureeDiffusion);
                req.setAttribute("nbposte",nbPostes);
                req.setAttribute("descritionposte",descriptionPoste);
                req.setAttribute("descriptionprofil",descriptionProfil);
                req.setAttribute("tel",telephone);
                req.setAttribute("email",email);
                req.setAttribute("typecontrat",TypeContrat);
                req.setAttribute("metier",Metier);
                req.setAttribute("domaine",DomaineMetier);
                req.setAttribute("client",Client);
                
           
        } catch (SQLException ex) {
            System.out.println("Une erreur est survenue" + ex);
        }
        
        RequestDispatcher rq = req.getRequestDispatcher("Pages/Offre.jsp");
        rq.forward(req, resp);
        
        ConnexionBDD.CloseDB(cnx);      
        
    }
}
