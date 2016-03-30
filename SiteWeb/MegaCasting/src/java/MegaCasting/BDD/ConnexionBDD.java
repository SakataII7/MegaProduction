package MegaCasting.BDD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnexionBDD
{
    
    public static Connection OpenDB() //Connexion
    {    
        //Chargement du driver SQLServeur
        try 
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

            System.exit(-1);
        }        

        //Connexion à la base de données
            Connection cnx = null;  
        try
        {
            String url = "jdbc:jtds:sqlserver://PC-ALEXANDRE:1433/MegaCastings";
            //String url = "jdbc:jtds:sqlserver://PC-Valentin:1433/MegaCastings"
            
            String login = "sa";
            String mdp = "admin";

            cnx = DriverManager.getConnection(url, login, mdp);
            System.out.println("Connexion établie");

            return cnx;

        } catch (SQLException ex){
            ex.printStackTrace();
        }        
            return null;
        }
    
    public static void CloseDB(Connection cnx) //Fermeture
    {        
        //Déconnexion de la base de données
        if(cnx != null)
        {
            try 
            {
                cnx.close();
            } catch (SQLException ex){

            }
        }
    }
}

