<%@page import="MegaCasting.classes.Pagination"%>
<%@page import="java.sql.Date"%>
<%@page import="MegaCasting.classes.Offre"%>
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
       
        <form id='listOffre' action="listOffre" method="post">
            <div class="center">
                <input type="hidden" name="offre" id="offre"/>
                <% List<String> NomOffre = (List<String>) request.getAttribute("nomoffre");
                   List<Long> IdOffre = (List<Long>) request.getAttribute("idoffre");
                   List<Date> DebutOffre = (List<Date>) request.getAttribute("debutcontrat");
                   List<Long> NbPoste = (List<Long>) request.getAttribute("nbpostes");
                   int i = 0;
                %>
                
                <%while(i < NomOffre.size()){ %>                    
                    <button type="button" onclick="document.getElementById('offre').value = '<%=IdOffre.get(i)%>'; document.getElementById('listOffre').submit();">
                        <span class="Nomoffre"><%=NomOffre.get(i)%></span></br></br>
                        <span class="DebutOffre"><%=DebutOffre.get(i)%></span>
                        <span class="NbPoste">Postes:<%=NbPoste.get(i)%></span>
                    </button> 
                <% i++;} %>
            </div>
        </form>
    </body>
</html>
