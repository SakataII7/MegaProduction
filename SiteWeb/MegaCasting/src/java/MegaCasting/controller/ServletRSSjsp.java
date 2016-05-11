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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Ikiuchi
 */
@WebServlet(name = "ServletRSSjsp", urlPatterns = {"/RSS"})
public class ServletRSSjsp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection cnx = ConnexionBDD.OpenDB();

        Statement stmt = null;

        try {

            //Création des listes qui auront les résultats des requêtes SQL
            List<String> NomOffre = new ArrayList<>();
            List<String> Reference = new ArrayList<>();
            List<Date> DatePub = new ArrayList<>();
            List<Date> DebutContrat = new ArrayList<>();
            List<Long> DureeDiff = new ArrayList<>();
            List<Long> NombresPostes = new ArrayList<>();
            List<String> DescrPoste = new ArrayList<>();
            List<String> DescrProfil = new ArrayList<>();
            List<String> Tel = new ArrayList<>();
            List<String> Mail = new ArrayList<>();
            List<String> Domaine = new ArrayList<>();
            List<String> Metier = new ArrayList<>();
            List<String> Type = new ArrayList<>();
            List<String> Client = new ArrayList<>();


            stmt = cnx.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT Intitule, Reference, DatePublication, DateDebutContrat, DureeDiffusion, NbPostes, DescriptionPoste, DescriptionProfil, Offre.Telephone, Offre.Email, Metier.Libelle AS Metier, DomaineMetier.Libelle AS Domaine, TypeContrat.Libelle AS Type, Client.Libelle AS Client FROM Offre LEFT JOIN Metier ON IdentifiantMetier = Metier.Identifiant LEFT JOIN DomaineMetier ON Metier.IdentifiantDomaineMetier = DomaineMetier.Identifiant LEFT JOIN Client ON Offre.IdentifiantClient = Client.Identifiant LEFT JOIN TypeContrat ON Offre.IdentifiantTypeContrat = TypeContrat.Identifiant");

            while (rs.next()) {

                NomOffre.add(rs.getString("Intitule"));
                Reference.add(rs.getString("Reference"));
                DatePub.add(rs.getDate("DatePublication"));
                DebutContrat.add(rs.getDate("DateDebutContrat"));
                DureeDiff.add(rs.getLong("DureeDiffusion"));
                NombresPostes.add(rs.getLong("NbPostes"));
                DescrPoste.add(rs.getString("DescriptionPoste"));
                DescrProfil.add(rs.getString("DescriptionProfil"));
                Tel.add(rs.getString("Telephone"));
                Mail.add(rs.getString("Email"));
                Domaine.add(rs.getString("Domaine"));
                Metier.add(rs.getString("Metier"));
                Type.add(rs.getString("Type"));
                Client.add(rs.getString("Client"));                
            }
            
            req.setAttribute("nomoffre", NomOffre);
            req.setAttribute("ref", Reference);
            req.setAttribute("publication", DatePub);
            req.setAttribute("debutcontrat", DebutContrat);
            req.setAttribute("dureediff", DureeDiff);
            req.setAttribute("nbposte", NombresPostes);
            req.setAttribute("descritionposte", DescrPoste);
            req.setAttribute("descriptionprofil", DescrProfil);
            req.setAttribute("tel", Tel);
            req.setAttribute("email", Mail);
            req.setAttribute("typecontrat", Domaine);
            req.setAttribute("metier", Metier);
            req.setAttribute("domaine", Type);
            req.setAttribute("client", Client);

        } catch (SQLException ex) {
            System.out.println("Une erreur est survenue" + ex);
        }
        RequestDispatcher rq = req.getRequestDispatcher("Pages/FluxRSS.jsp");
        rq.forward(req, resp);
        
        ConnexionBDD.CloseDB(cnx);
    }
}
