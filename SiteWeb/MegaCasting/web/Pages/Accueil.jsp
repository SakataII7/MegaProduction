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
                    <a class="navbar-brand" href="#">MegaCasting</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="accueil">Accueil</a></li>

                        <ul class="menu">
                            <li><a href="#" class="btn btn-default dropdown-toggle">Domaines Métiers<span class="caret"></span></a>
                                <ul>
                                    <form id='DomaineAccueil' action="accueil" method="post">
                                        <input type="hidden" name="UneRecherche" id="UneRecherche"/>
                                        <% List<String> NomCat = (List<String>) request.getAttribute("nomcategorie");
                                            List<Long> IdCat = (List<Long>) request.getAttribute("idcategorie");
                                            int i = 0;
                                        %>

                                        <%while (i < NomCat.size()) {%>                    
                                        <li><button type="button" onclick="document.getElementById('UneRecherche').value = '<%=IdCat.get(i)%>';
                                                            document.getElementById('DomaineAccueil').submit();">
                                                <span><%=NomCat.get(i)%></span></br>
                                            </button></li>
                                            <% i++;
                                                            } %>
                                    </form>
                                </ul>
                            </li>
                        </ul>
                    </ul>
                    <form class="navbar-form navbar-left" id='FormSearch' action="accueil" method="post">
                        <div class="form-group">
                            <input type="text" name="search" class="form-control" placeholder="Search">
                        </div>

                        <button type="submit" class="btn btn-default">Submit</button>
                </div>
                </form>
                </form>

        </nav>










        <form id='offre' action="offre" method="post">
            <div class="center">
                <input type="hidden" name="UneOffre" id="UneOffre"/>
                <% List<String> NomOffre = (List<String>) request.getAttribute("nomoffre");
                    List<Long> IdOffre = (List<Long>) request.getAttribute("idoffre");
                    List<Date> DebutOffre = (List<Date>) request.getAttribute("debutcontrat");
                    List<Long> NbPoste = (List<Long>) request.getAttribute("nbpostes");
                    int j = 0;
                %>

                <%while (j < NomOffre.size()) {%>                    
                <button type="button" class="BoutonOffre" onclick="document.getElementById('UneOffre').value = '<%=IdOffre.get(j)%>';
                        document.getElementById('offre').submit();">
                    <span class="Nomoffre"><%=NomOffre.get(j)%></span></br></br>
                    <span class="DebutOffre"><%=DebutOffre.get(j)%></span>
                    <span class="NbPoste">Postes:<%=NbPoste.get(j)%></span>
                </button> 
                <% j++;
                    }%>
            </div>
        </form>
        <script src="js/bootstrap.min.js"/>
        <script   src="https://code.jquery.com/jquery-2.2.3.min.js"   integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    </body>
</html>
