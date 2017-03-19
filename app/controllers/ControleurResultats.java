package controllers;

import java.util.*;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;

public class ControleurResultats extends Controller {
    
   Connection connection = DB.getConnection();

   public Result afficherResultats(String niveau) {
        try {

            Statement statement = connection.createStatement();
            String query = "SELECT Nom_utilisateur, temps, Date_score " +
                           "FROM score, niveau " + 
                           "WHERE '" + niveau + "' = niveau.nom AND score.Id_niveau = niveau.Id_niveau " +
                           "ORDER BY temps " +
                           "LIMIT 10;";
 
            ResultSet res = statement.executeQuery(query);             
            List<String> table = new ArrayList<String>(); 
            
            while(res.next()){
                table.add(String.format("%-10s ->      %-12s (%-10s)", res.getString(1), correctTemps(res.getString(2)), res.getString(3)));
            }            
            
            query = "SELECT nom " +
                    "FROM niveau " +
                    "ORDER BY nom;";

            res = statement.executeQuery(query);
            List<String> practices = new ArrayList<String>();
            List<String> niveaux = new ArrayList<String>();

            while(res.next()){
                if (res.getString(1).startsWith("p")){
                   practices.add(res.getString(1));
                }
                else {
                   niveaux.add(res.getString(1));
                } 
            }
            
            //return ok(resultats.render(niveau, table));
            return ok(resultats_buttons.render(niveau, table, practices, niveaux));

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
