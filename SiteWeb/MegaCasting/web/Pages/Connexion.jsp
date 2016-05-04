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
        
        <h1 class="titre">Connexion</h1>
        <div class="connexion">
        <%String message = (String) request.getAttribute("msg");%>
        
        <p class="rouge"><%=message%></p>
        
        <form action="connexion" method="post">
            <p>Identifiant : </br><input type="text" name="login" class="form-control" placeholder="Login"></p>
            <p>Mot de passe : </br><input type="password" name="password" class="form-control" placeholder="Password"/></p>
            <input class="btn btn-default" type="submit" value="Connexion" name="Connexion" />            
        </form>
        </div>
        <script src="js/bootstrap.min.js"/>
    </body>
</html>
