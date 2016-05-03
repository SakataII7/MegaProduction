<%-- 
    Document   : Connexion
    Created on : 4 avr. 2016, 18:24:00
    Author     : Ikiuchi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/MegaCSS.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 class="titre">Connexion</h1>
        <span class="connexion">
        <%String message = (String) request.getAttribute("msg");%>
        
        <p><%=message%></p>
        
        <form action="connexion" method="post">
            <p>Identifiant : </br><input type="text" name="login" placeholder="Login"/></p>
            <p>Mot de passe : </br><input type="password" name="password" placeholder="Password"/></p>
            <input type="submit" value="Connexion" name="Connexion" />            
        </form>
        </span>
    </body>
</html>
