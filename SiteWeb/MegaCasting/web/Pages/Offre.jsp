<%-- 
    Document   : Offre
    Created on : 4 avr. 2016, 22:09:12
    Author     : Ikiuchi
--%>

<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/MegaCSS.css">
        <title>JSP Page</title>
    </head>
    <body>
        
        <% String NomOffre = (String) request.getAttribute("nomoffre");
           Long IdOffre = (Long) request.getAttribute("idoffre");
           String Ref = (String) request.getAttribute("ref");
           Date Publication = (Date) request.getAttribute("publication");
           Date DebutContrat = (Date) request.getAttribute("debutcontrat");
           Long DureeDiff = (Long) request.getAttribute("dureediff");
           Long NbPostes = (Long) request.getAttribute("nbposte");
           String DescriptionPoste = (String) request.getAttribute("descritionposte");
           String DescriptionProfil = (String) request.getAttribute("descriptionprofil");
           String Tel = (String) request.getAttribute("tel");
           String Email = (String) request.getAttribute("email");
           String TypeContrat = (String) request.getAttribute("typecontrat");
           String Metier = (String) request.getAttribute("metier");
           String Domaine = (String) request.getAttribute("domaine");
           String Client = (String) request.getAttribute("client");

         %>
            <div>
                <div class="Offre">
                    <h1>IdOffre : <%=IdOffre%></h1>
                    <h1>Nom : <%=NomOffre%></h1>
                    <h1>Réference : <%=Ref%></h1>
                    <h1>Publié le : <%=Publication%></h1>
                    <h1>Début du contrat : <%=DebutContrat%></h1>
                    <h1>Durée de Diffusion : <%=DureeDiff%></h1>
                    <h1>Nombre de postes :<%=NbPostes%></h1>
                    <h1>Description du poste : <%=DescriptionPoste%></h1>
                    <h1>Description du profil : <%=DescriptionProfil%></h1>
                    <h1>Tel : <%=Tel%></h1>
                    <h1>Email : <%=Email%></h1>
                    <h1>Type de contrat : <%=TypeContrat%></h1>
                    <h1>Domaine du métier : <%=Domaine%></h1>
                    <h1>Métier : <%=Metier%></h1>
                    <h1>Client : <%=Client%></h1>
                </div>
        </div>
    </body>
</html>
