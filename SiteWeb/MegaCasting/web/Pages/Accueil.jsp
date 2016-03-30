<%@page import="MegaCasting.classes.Offre"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Accueil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1 class="SiteTitle">MegaCasting</h1>
       
        <form id='ListOffre' action="ListOffre" method="post">
            <div class="center">
                <input type="hidden" name="offre" id="offre"/>
                <% List<String> Nomoffre = (List<String>) request.getAttribute("nomoffre"); 
                   List<Long> IDoffre = (List<Long>) request.getAttribute("idoffre"); 
                   int i = 0;
                %>

                <%while(i < Nomoffre.size()){ %>                    
                    <button type="button" class="offre" onclick="document.getElementById('offre').value = '<%=IDoffre.get(i)%>'; document.getElementById('ListOffre').submit();">
                            <%=Nomoffre.get(i)%>
                    </button> 
                <% i++;} %>          
            </div>
        </form>
    </body>
</html>
