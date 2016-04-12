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
        <link rel="stylesheet" href="CSS/MegaCSS.css">
    </head>
    <body>
        <h1 class="titre">MegaCasting</h1>
        
        <a href="http://localhost:8080/MegaCasting/connexion">Connexion</a>
        
        <form id='offre' action="offre" method="post">
            <div class="center">
                <input type="hidden" name="UneOffre" id="UneOffre"/>
                <% List<String> NomOffre = (List<String>) request.getAttribute("nomoffre");
                   List<Long> IdOffre = (List<Long>) request.getAttribute("idoffre");
                   List<Date> DebutOffre = (List<Date>) request.getAttribute("debutcontrat");
                   List<Long> NbPoste = (List<Long>) request.getAttribute("nbpostes");
                   int i = 0;
                %>
                
                <%while(i < NomOffre.size()){ %>                    
                    <button type="button" onclick="document.getElementById('UneOffre').value = '<%=IdOffre.get(i)%>'; document.getElementById('offre').submit();">
                        <span class="Nomoffre"><%=NomOffre.get(i)%></span></br></br>
                        <span class="DebutOffre"><%=DebutOffre.get(i)%></span>
                        <span class="NbPoste">Postes:<%=NbPoste.get(i)%></span>
                    </button> 
                <% i++;} %>
            </div>
        </form>
    </body>
</html>
