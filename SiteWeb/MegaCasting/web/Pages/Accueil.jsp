<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="MegaCasting.classes.Offre"%>
<%@page import="MegaCasting.classes.ViewOffre"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Accueil</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" href="CSS/MegaCSS.css">
    </head>
    <body>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="accueil">MegaCasting</a>
            </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="connexion">Connexion</a></li>
                <span class="menu">
                    <li><a href="#" class="btn btn-default dropdown-toggle">Domaines Métiers<span class="caret"></span></a>
                        <ul>
                            <!-- Formulaire qui envoie la valeur de la catégorie selectionné -->
                            <form id='DomaineAccueil' action="accueil" method="post">
                                <input type="hidden" name="UneRecherche" id="UneRecherche"/>
                                <% List<String> NomCat = (List<String>) request.getAttribute("nomcategorie");
                                    List<Long> IdCat = (List<Long>) request.getAttribute("idcategorie");
                                    int i = 0;
                                %>
                                <!-- affichage des boutons des différentes catégories-->
                                <%while (i < NomCat.size()) {%>                    
                                <li><button class="btn btn-default" type="button" onclick="document.getElementById('UneRecherche').value = '<%=IdCat.get(i)%>';
                                        document.getElementById('DomaineAccueil').submit();">
                                        <span><%=NomCat.get(i)%></span></br>
                                    </button></li>
                                    <% i++;
                                        } %>
                            </form>
                        </ul>
                    </li>
                </span>
            </ul>
            <!-- Formulaire qui envoie la valeur de recherche de la textbox -->
            <form class="navbar-form" id='FormSearch' action="accueil" method="post">
                <div class="form-group">
                    <input type="text" name="search" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </nav>
       <!-- Affichage des offres (quelques info seulement) et possibilité de cliquer pour voir l'offre complète -->                     
    <form id='offre' action="offre" method="post">
        <div class="center">
            <input type="hidden" name="UneOffre" id="UneOffre"/>
            <% List<String> NomOffre = (List<String>) request.getAttribute("nomoffre");
                List<Long> IdOffre = (List<Long>) request.getAttribute("idoffre");
                List<Date> DebutOffre = (List<Date>) request.getAttribute("debutcontrat");
                List<Long> NbPoste = (List<Long>) request.getAttribute("nbpostes");
                List<String> Domaine = (List<String>) request.getAttribute("domaine");
                                
                int j = 0;
            %>

            <%while (j < NomOffre.size()) {%>                    
            <button type="button" class="BoutonOffre" onclick="document.getElementById('UneOffre').value = '<%=IdOffre.get(j)%>';
                    document.getElementById('offre').submit();">
                <span class="Nomoffre"><%=NomOffre.get(j)%></span></br></br>
                <%if(Domaine.get(j) != null){%>
                <span class="DebutOffre"><%=Domaine.get(j)%></span></br>
                <%}%>
                <span class="DebutOffre">Debut du Contrat: <%=DebutOffre.get(j)%></span>
                <span class="NbPoste">Postes :<%=NbPoste.get(j)%></span>
                
            </button> 
            <% j++;
                }%>
        </div>
    </form>
    <!-- pagination -->
    <form id='Pagination' action="accueil" method="post">
        <input type="hidden" name="Pagine" id="Pagine"/>
        <input type="hidden" name="RequetePrecedente" id="RequetePrecedente"/>
        <input type="hidden" name="RequetePrecedente2" id="RequetePrecedente2"/>
        <nav class="center">
            
                <%
                    Long RequeteDomaine = (Long) request.getAttribute("RequeteDomaine");
                    String RequeteRecherche = (String) request.getAttribute("RequeteRecherche");
                   
                    int k = 0;                    
                    int offset;
                    double num;
                    Long Total = (Long) request.getAttribute("total");
                    //Parse le Total en double sinon le résultat n'est pas correct (j'ai besoin d'arrondir au supérieur si le résultat de la division n'est pas un entier)
                    double total = (double) (long) Total;
                    num = total / 4;
                %>
                <!-- Renvoie les valeurs des requêtes précédentes dans les if{} s'il y en a eu -->
                <%while (k < num) {%>
                    <% offset = k*4; %>
                    <button type="button" class="btn btn-default" onclick="document.getElementById('Pagine').value = '<%=offset%>';
                        
                        <%if(RequeteDomaine != null){%>
                            document.getElementById('RequetePrecedente').value = '<%=RequeteDomaine%>';
                        <%}%>
                        <%if(RequeteRecherche != null){%>
                            document.getElementById('RequetePrecedente2').value = '<%=RequeteRecherche%>';
                        <%}%>
                            document.getElementById('Pagination').submit();">
                        <span><%=k + 1%></span>
                    </button>
                <% k++; }%>
               
                
            
        </nav>
    </form>

    <script src="js/bootstrap.min.js"/>
    </body>
</html>
