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
        <link rel="stylesheet" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" href="CSS/MegaCSS.css">
        <title>JSP Page</title>
    </head>
    <body>
        
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">MegaCasting</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                    <li><a href="accueil">Accueil</a></li>
                </div>
            </div>
        </nav>
        
        <% String NomOffre = (String) request.getAttribute("nomoffre");
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
         <!-- Affichage de l'offre -->
         <div>
                <div class="Offre">
                    <h1 class="fond">Nom : <%=NomOffre%></h1>
                    <h1 class="fond">Réference : <%=Ref%></h1>
                    <h1 class="fond">Publié le : <%=Publication%></h1>
                    <h1 class="fond">Début du contrat : <%=DebutContrat%></h1>
                    <h1 class="fond">Durée de Diffusion : <%=DureeDiff%></h1>
                    <h1 class="fond">Nombre de postes :<%=NbPostes%></h1>
                    <h1 class="fond">Description du poste : <%=DescriptionPoste%></h1>
                    <h1 class="fond">Description du profil : <%=DescriptionProfil%></h1>
                    <h1 class="fond">Tel : <%=Tel%></h1>
                    <h1 class="fond">Email : <%=Email%></h1>
                    <h1 class="fond">Type de contrat : <%=TypeContrat%></h1>
                    <h1 class="fond">Domaine du métier : <%=Domaine%></h1>
                    <h1 class="fond">Métier : <%=Metier%></h1>
                    <h1 class="fond">Client : <%=Client%></h1>
                </div>
        </div>
        <script src="js/bootstrap.min.js"/>
    </body>
</html>
