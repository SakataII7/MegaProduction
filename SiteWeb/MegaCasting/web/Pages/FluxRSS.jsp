<%-- 
    Document   : FluxRSS
    Created on : 11 mai 2016, 11:07:27
    Author     : Ikiuchi
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FluxRSS</title>
    </head>
    <body>
        <% List<String> NomOffre = (List<String>) request.getAttribute("nomoffre");
           List<String> Ref = (List<String>) request.getAttribute("ref");
           List<Date> Publication = (List<Date>) request.getAttribute("publication");
           List<Date> DebutContrat = (List<Date>) request.getAttribute("debutcontrat");
           List<Long> DureeDiff = (List<Long>) request.getAttribute("dureediff");
           List<Long> NbPostes = (List<Long>) request.getAttribute("nbposte");
           List<String> DescriptionPoste = (List<String>) request.getAttribute("descritionposte");
           List<String> DescriptionProfil = (List<String>) request.getAttribute("descriptionprofil");
           List<String> Tel = (List<String>) request.getAttribute("tel");
           List<String> Email = (List<String>) request.getAttribute("email");
           List<String> TypeContrat = (List<String>) request.getAttribute("typecontrat");
           List<String> Metier = (List<String>) request.getAttribute("metier");
           List<String> Domaine = (List<String>) request.getAttribute("domaine");
           List<String> Client = (List<String>) request.getAttribute("client");
           
           int i = 0;
        %>

        <%while (i < NomOffre.size()) {%>              
        <pre>
            <title><%=NomOffre.get(i)%></title><br/>
            <reference><%=Ref.get(i)%></reference><br/>
            <pubDate><%=Publication.get(i)%></pubDate><br/>
            <DateDebutContrat><%=DebutContrat.get(i)%></DateDebutContrat><br/>
            <DureeDiffusion><%=DureeDiff.get(i)%></DureeDiffusion><br/>
            <NombrePoste><%=NbPostes.get(i)%></NombrePoste><br/>
            <descriptionPoste><%=DescriptionPoste.get(i)%></descriptionPoste><br/>
            <descriptionProfil><%=DescriptionProfil.get(i)%></descriptionProfil><br/>
            <tel><%=Tel.get(i)%></tel><br/>
            <email><%=Email.get(i)%></email><br/>
            <typeContrat><%=TypeContrat.get(i)%></typeContrat><br/>
            <domaine><%=Domaine.get(i)%></domaine><br/>
            <metier><%=Metier.get(i)%></metier><br/>
            <client><%=Client.get(i)%></client><br/><br/>
            </pre>
        <% i++;
                    }%>
</body>
</html>
