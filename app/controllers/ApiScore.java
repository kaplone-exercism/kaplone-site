package controllers;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;

public class ApiScore extends Controller {

    Connection connection = DB.getConnection();

    public Result soumissionScore(String user) {
       RequestBody body = request().body();
       
       String niveau = body.asText().split("=")[0].trim();
       
       try {

            Statement statement = connection.createStatement();
            String query = "SELECT count(*) " +
                           "FROM score, niveau " +
                           "WHERE '" + niveau + "' = niveau.nom AND '" + user + "' = score.Nom_utilisateur AND score.Id_niveau = niveau.Id_niveau;";
           
            ResultSet res = statement.executeQuery(query);
            res.next();
            boolean present = res.getInt(1) == 1;
            
            query = "SELECT Id_niveau " +
                    "FROM  niveau " +
                    "WHERE '" + niveau + "' = niveau.nom;";

            res = statement.executeQuery(query);
            res.next();
            int id_niveau_int = res.getInt(1);
            
            if (present){
                query = "UPDATE score " +
                        "SET temps = '" + body.asText().split("=")[1].trim() + "' , date_score = CURRENT_TIMESTAMP " +
                        "WHERE nom_utilisateur = '" + user + "' AND Id_niveau = '" + id_niveau_int + "';";
            }
            else {
                query = "INSERT INTO score " +
                        "(temps, Date_score, Nom_utilisateur, Id_niveau) " +
                        "VALUES('" + body.asText().split("=")[1].trim() + "', CURRENT_TIMESTAMP, '" + user + "', " + id_niveau_int + ");";
            } 
            
            int resultat = statement.executeUpdate(query);

            return ok("nombre d'enregistrements crees/modifies : " + resultat);
        }
        catch (SQLException se){
            return ok(se.toString());
        }     
     }
    
    private String correctTemps(String t){
        if (t.length() == 10) return t.substring(3,10);
        else return t.substring(3,8) + ".0";
    }

}

