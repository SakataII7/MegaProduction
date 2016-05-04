
package MegaCasting.controller;

import MegaCasting.BDD.ConnexionBDD;
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
        String identifiantDomaine2 = req.getParameter("RequetePrecedente");
        
        String recherche = req.getParameter("search");
        String recherche2 = req.getParameter("RequetePrecedente2");  
        
        String offset = req.getParameter("Pagine");
        int num = 0;
        
        //Condition pour la pagination
        if(identifiantDomaine2 != null)
        {
            if(identifiantDomaine2.equals(""))
            {
                identifiantDomaine2 = null;
            }
        }
        if(recherche2 != null)
        {  
            if(recherche2.equals(""))
            {
                recherche2 = null;
            }
        }
        if(offset != null)
        {
            num = Integer.parseInt(offset);
        }
            
        try {
            
            //Création des listes qui auront les résultats des requêtes SQL
            List<String> NomCategorie = new ArrayList<>();
            List<Long> IdCategorie = new ArrayList<>();
            List<String> NomOffre = new ArrayList<>();
            List<Long> IDoffre = new ArrayList<>();
            List<Date> DebutContrat = new ArrayList<>();
            List<Long> NombresPostes = new ArrayList<>();
            List<String> Domaine = new ArrayList<>();
            
            int total = 0;

            stmt = cnx.createStatement();
            PreparedStatement pstmt = null;
            
            //Lance la bonne requête pour l'affichage des offres
            //recherche quand un élément est mis dans la textbox
            if (recherche != null || recherche2 != null)
            {
                if (recherche != null)
                {
                    recherche = '%'+recherche+'%'; //Le % ne fonctionnait pas dans la requête SQL
                    //récupère les informations qu'on affiche sur la page d'accueil 
                    pstmt = cnx.prepareStatement("SELECT Offre.Identifiant, Intitule, DateDebutContrat, NbPostes, IdentifiantDomaineMetier, DomaineMetier.Libelle AS Domaine FROM Offre LEFT JOIN DomaineMetier ON IdentifiantDomaineMetier = DomaineMetier.Identifiant WHERE Intitule LIKE ? ORDER BY Intitule OFFSET ? Rows FETCH NEXT 4 ROWS ONLY");
                    pstmt.setString(1, recherche);
                    pstmt.setInt(2, num);
                }else{
                    recherche2 = '%'+recherche2+'%'; //Le % ne fonctionnait pas dans la requête SQL
                    //récupère les informations qu'on affiche sur la page d'accueil 
                    pstmt = cnx.prepareStatement("SELECT Offre.Identifiant, Intitule, DateDebutContrat, NbPostes, IdentifiantDomaineMetier, DomaineMetier.Libelle AS Domaine FROM Offre LEFT JOIN DomaineMetier ON IdentifiantDomaineMetier = DomaineMetier.Identifiant WHERE Intitule LIKE ? ORDER BY Intitule OFFSET ? Rows FETCH NEXT 4 ROWS ONLY");
                    pstmt.setString(1, recherche2);
                    pstmt.setInt(2, num);
                }
               
                ResultSet rs = pstmt.executeQuery();
                
                while(rs.next())
                {                
                    NomOffre.add( rs.getString("Intitule"));
                    IDoffre.add(rs.getLong("Identifiant"));
                    DebutContrat.add(rs.getDate("DateDebutContrat")); 
                    NombresPostes.add( rs.getLong("NbPostes"));
                    Domaine.add( rs.getString("Domaine"));
                }
                
                if (recherche != null)
                {
                    //Requête spécial pour avoir le nombre de résultat: fait pour chaque requête
                    pstmt = cnx.prepareStatement("SELECT COUNT(Identifiant) as Total FROM Offre WHERE Intitule LIKE ?");
                    pstmt.setString(1, recherche);
                    req.setAttribute("RequeteRecherche", recherche);
                }else{
                    pstmt = cnx.prepareStatement("SELECT COUNT(Identifiant) as Total FROM Offre WHERE Intitule LIKE ?");
                    pstmt.setString(1, recherche2);
                    req.setAttribute("RequeteRecherche", recherche2);
                }
                
                ResultSet rsCount = pstmt.executeQuery();
                
                while(rsCount.next())
                {                
                    total = rsCount.getInt("Total");
                }
                
            }
            //identifiantDomaine quand l'utilisateur clique sur un domaine
            else if(identifiantDomaine != null || identifiantDomaine2 != null)
            {
                if(identifiantDomaine != null)
                {
                    Long idDomaine = Long.parseLong(identifiantDomaine);
                    pstmt = cnx.prepareStatement("SELECT Offre.Identifiant, Intitule, DateDebutContrat, NbPostes, IdentifiantDomaineMetier, DomaineMetier.Libelle AS Domaine FROM Offre LEFT JOIN DomaineMetier ON IdentifiantDomaineMetier = DomaineMetier.Identifiant WHERE Offre.IdentifiantDomaineMetier = ? ORDER BY Intitule OFFSET ? Rows FETCH NEXT 4 ROWS ONLY");
                    pstmt.setLong(1, idDomaine);
                    pstmt.setInt(2, num);
                }else{
                    Long idDomaine = Long.parseLong(identifiantDomaine2);
                    pstmt = cnx.prepareStatement("SELECT Offre.Identifiant, Intitule, DateDebutContrat, NbPostes, IdentifiantDomaineMetier, DomaineMetier.Libelle AS Domaine FROM Offre LEFT JOIN DomaineMetier ON IdentifiantDomaineMetier = DomaineMetier.Identifiant WHERE Offre.IdentifiantDomaineMetier = ? ORDER BY Intitule OFFSET ? Rows FETCH NEXT 4 ROWS ONLY");
                    pstmt.setLong(1, idDomaine);
                    pstmt.setInt(2, num);
                }
                
                ResultSet rs = pstmt.executeQuery();
                
                while(rs.next())
                {                
                    NomOffre.add( rs.getString("Intitule"));
                    IDoffre.add(rs.getLong("Identifiant"));
                    DebutContrat.add(rs.getDate("DateDebutContrat")); 
                    NombresPostes.add( rs.getLong("NbPostes"));
                    Domaine.add( rs.getString("Domaine"));
                }
                
                if(identifiantDomaine != null)
                {
                    Long idDomaine = Long.parseLong(identifiantDomaine);
                    pstmt = cnx.prepareStatement("SELECT COUNT(Identifiant) as Total FROM Offre WHERE Offre.IdentifiantDomaineMetier = ?");
                    pstmt.setLong(1, idDomaine);
                    req.setAttribute("RequeteDomaine", idDomaine);
                }else{
                    Long idDomaine = Long.parseLong(identifiantDomaine2);
                    pstmt = cnx.prepareStatement("SELECT COUNT(Identifiant) as Total FROM Offre WHERE Offre.IdentifiantDomaineMetier = ?");
                    pstmt.setLong(1, idDomaine);
                    req.setAttribute("RequeteDomaine", idDomaine);
                }
                    ResultSet rsCount = pstmt.executeQuery();
                    
                while(rsCount.next())
                {                
                    total = rsCount.getInt("Total");
                }
            }
            //quand on clique sur l'accueil ou la première fois qu'on arrive sur la page
            else
            {
                pstmt = cnx.prepareStatement("SELECT Offre.Identifiant, Intitule, DateDebutContrat, NbPostes, IdentifiantDomaineMetier, DomaineMetier.Libelle AS Domaine FROM Offre LEFT JOIN DomaineMetier ON IdentifiantDomaineMetier = DomaineMetier.Identifiant ORDER BY Intitule OFFSET ? Rows FETCH NEXT 4 ROWS ONLY");
                pstmt.setInt(1, num);    
                ResultSet rs = pstmt.executeQuery();
                
                while(rs.next())
                {                
                    NomOffre.add( rs.getString("Intitule"));
                    IDoffre.add(rs.getLong("Identifiant"));
                    DebutContrat.add(rs.getDate("DateDebutContrat")); 
                    NombresPostes.add( rs.getLong("NbPostes"));
                    Domaine.add( rs.getString("Domaine"));
                }
                
                ResultSet rsCount = stmt.executeQuery("SELECT COUNT(*) as Total FROM Offre");
                
                while(rsCount.next())
                {                
                    total = rsCount.getInt("Total");
                }
            }

                req.setAttribute("idoffre", IDoffre);
                req.setAttribute("nomoffre", NomOffre);
                req.setAttribute("debutcontrat", DebutContrat);
                req.setAttribute("nbpostes", NombresPostes);
                req.setAttribute("domaine", Domaine);
                
                Long L = new Long(total);
                req.setAttribute("total", L);
                
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