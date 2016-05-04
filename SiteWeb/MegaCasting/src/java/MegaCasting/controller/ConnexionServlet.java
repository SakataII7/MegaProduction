/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MegaCasting.controller;

import MegaCasting.BDD.ConnexionBDD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ikiuchi
 */
@WebServlet(name = "ConnexionServlet", urlPatterns = {"/connexion"})

public class ConnexionServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection cnx = ConnexionBDD.OpenDB();

        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        
        String message = "";
        Statement stmt = null;
        
        boolean erreur = false;

        if (login == null || password == null) {
            message = "Veuillez vous identifier !";
            erreur = true;
        } else if (login.isEmpty() || password.isEmpty()) {
            message = "Ne rien mettre ne fonctionnera pas...";
            erreur = true;

        } else {
            try {
                stmt = cnx.createStatement();

                ResultSet rs = stmt.executeQuery(""
                        + "SELECT Password, Login "
                        + "FROM Connexion ");

                
                while (rs.next()) {
                    String passwordDB = rs.getString("Password");
                    String loginDB = rs.getString("Login");

                    if (login.equals(loginDB) && password.equals(passwordDB)) //Quand le login et le password correspond
                    {
                       
                        RequestDispatcher rq = req.getRequestDispatcher("rss");
                        rq.forward(req, resp);
                        erreur = false;
                        break;
               
                    } else {
                        message = "Mauvais mot de passe ou identifiant";
                        erreur = true;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("une erreur est survenue" + ex);
            }
        }
        
        req.setAttribute("msg", message);
        if (erreur) {
            RequestDispatcher rq2 = req.getRequestDispatcher("Pages/Connexion.jsp");
            rq2.forward(req, resp);
        }

        ConnexionBDD.CloseDB(cnx);
    }
}