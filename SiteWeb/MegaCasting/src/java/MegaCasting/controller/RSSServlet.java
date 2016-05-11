package MegaCasting.controller;

import MegaCasting.BDD.ConnexionBDD;
import java.io.File;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


@WebServlet(name = "RSSServlet", urlPatterns = {"/rss"})
public class RSSServlet extends HttpServlet {

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
        resp.setContentType("text/html;charset=UTF-8");

        Connection cnx = ConnexionBDD.OpenDB();

        Statement stmt = null;
        
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            stmt = cnx.createStatement();

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

            //On cherche les produits de la catégorie dont on a récupéré l'id, tout est sur la même ligne car il y avait des soucis
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
            
            final DocumentBuilder builder = factory.newDocumentBuilder();
	    		
	    /*
	     * Etape 3 : création d'un Document
	     */
	    final Document document= builder.newDocument();
					
	    /*
	     * Etape 4 : création de l'Element racine
	     */
	    final Element racine = document.createElement("channel");
	    document.appendChild(racine);

            //Création des List de string car le balises ne se remplissent qu'avec des Strings
            List<String> datepub = new ArrayList();
            List<String> debutcontrat = new ArrayList();
            List<String> dureeDiff = new ArrayList();
            List<String> nombresPostes = new ArrayList();
            List<String> domaine1 = new ArrayList();
            List<String> metier1 = new ArrayList();
            
            Element title1 = document.createElement("title");
            Element link1 = document.createElement("link");
                
            title1.appendChild(document.createTextNode("Offre MegaCastings"));
            link1.appendChild(document.createTextNode("172.16.1.149/MegaCasting/accueil"));
            racine.appendChild(title1);
            racine.appendChild(link1);

            int i = 0;

            while (i < NomOffre.size()) 
            {
                //Création des balises
                final Element item = document.createElement("item");
                final Element title = document.createElement("title");
                final Element reference = document.createElement("reference");
                final Element pubDate = document.createElement("pubDate");
                final Element debutDateContrat = document.createElement("DateDebutContrat");
                final Element dureeDiffusion = document.createElement("DureeDiffusion");
                final Element nombrePoste = document.createElement("NombrePoste");
                final Element descriptionPoste = document.createElement("descriptionPoste");
                final Element descriptionProfil = document.createElement("descriptionProfil");
                final Element tel = document.createElement("tel");
                final Element mail = document.createElement("mail");
                final Element typeContrat = document.createElement("typeContrat");
                final Element domaine = document.createElement("domaine");
                final Element metier = document.createElement("metier");
                final Element client = document.createElement("client");
                
                //Conversion en String des Listes car la suite ne se fait qu'avec des String
                datepub.add(String.valueOf(DatePub.get(i)));
                debutcontrat.add(String.valueOf(DebutContrat.get(i)));
                dureeDiff.add(String.valueOf(DureeDiff.get(i)));
                nombresPostes.add(String.valueOf(NombresPostes.get(i)));
                //Problème que je ne comprend pas le Libelle du domaine et du métier n'était pas reconnu comme string suite à une mauvaise manip avec git
                domaine1.add(String.valueOf(Domaine.get(i)));
                metier1.add(String.valueOf(Metier.get(i)));

//                //Remplissage des balises
                title.appendChild(document.createTextNode(NomOffre.get(i)));
                reference.appendChild(document.createTextNode(Reference.get(i)));
                pubDate.appendChild(document.createTextNode(datepub.get(i)));
                debutDateContrat.appendChild(document.createTextNode(debutcontrat.get(i)));
                dureeDiffusion.appendChild(document.createTextNode(dureeDiff.get(i)));
                nombrePoste.appendChild(document.createTextNode(nombresPostes.get(i)));
                descriptionPoste.appendChild(document.createTextNode(DescrPoste.get(i)));
                descriptionProfil.appendChild(document.createTextNode(DescrProfil.get(i)));
                tel.appendChild(document.createTextNode(Tel.get(i)));
                mail.appendChild(document.createTextNode(Mail.get(i)));
                typeContrat.appendChild(document.createTextNode(Type.get(i)));
                domaine.appendChild(document.createTextNode(domaine1.get(i)));
                metier.appendChild(document.createTextNode(metier1.get(i)));
                client.appendChild(document.createTextNode(Client.get(i)));

//                //place les balises
                item.appendChild(title);
                item.appendChild(reference);
                item.appendChild(pubDate);
                item.appendChild(debutDateContrat);
                item.appendChild(dureeDiffusion);
                item.appendChild(nombrePoste);
                item.appendChild(descriptionPoste);
                item.appendChild(descriptionProfil);
                item.appendChild(tel);
                item.appendChild(mail);
                item.appendChild(typeContrat);
                item.appendChild(domaine);
                item.appendChild(metier);
                item.appendChild(client);
              
                racine.appendChild(item);

                i++;
            }
	    /*
	     * Etape 8 : affichage
	     */
	    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    final Transformer transformer = transformerFactory.newTransformer();
	    final DOMSource source = new DOMSource(document);
	    final StreamResult sortie = new StreamResult(new File("C:\\fluxRSS.xml"));
	
	    //prologue
	    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");			
	    		
	    //formatage
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
	    //sortie
	    transformer.transform(source, sortie);	
            
        } catch (SQLException ex) {
            System.out.println("Une erreur est survenue" + ex);
        } catch (final ParserConfigurationException ex) {
            System.out.println("Une erreur est survenue" + ex);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        RequestDispatcher rq = req.getRequestDispatcher("Pages/fluxRSS.xml");
        rq.forward(req, resp);

        ConnexionBDD.CloseDB(cnx);
    }
}
