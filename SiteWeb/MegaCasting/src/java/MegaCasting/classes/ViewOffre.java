/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MegaCasting.classes;

import MegaCasting.BDD.ConnexionBDD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ikiuchi
 */
public class ViewOffre {
    
    private int noOfRecords;
    
     public List<Offre> VueOffre(int offset, int noOfRecords)
    {
        Connection cnx = ConnexionBDD.OpenDB();
        Statement stmt = null;
        List<Offre> listOffre = new ArrayList<Offre>();
        
        try {           
            stmt = cnx.createStatement();

            String query = "SELECT Identifiant, Intitule, DateDebutContrat, NbPostes FROM Offre " + offset + ", " + noOfRecords;
            
            Offre offre = null;
            ResultSet rs = stmt.executeQuery(query);
             
            while (rs.next()) {
                offre = new Offre();
                offre.setIdentifiant(rs.getInt("Identifiant"));
                offre.setIntitule(rs.getString("Intitule"));
                offre.setDateDebutContrat(rs.getDate("DateDebutContrat"));
                offre.setNbPostes(rs.getInt("NbPostes"));
                listOffre.add(offre);
            }
            rs.close();
             
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally
            {
            try {
                if(stmt != null)
                    stmt.close();
                if(cnx != null)
                    cnx.close();
                } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listOffre;
    }
 
    public int getNoOfRecords() {
        return noOfRecords;
    }
}
