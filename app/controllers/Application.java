package controllers;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;

public class Application extends Controller {
    
    Connection connection = DB.getConnection();

    public Result index() {
        //return ok("<h3>Serveur is up !</h3>").as("text/html");

        return ok("<!DOCTYPE html>\n" +
                "<html lang=\"fr\">\n" +
                "\n" +
                "<head>\n" +
                "    <title>Accueil</title>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color:black;\">\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<img src=\"/assets/images/index.jpg\">\n" +
                "</body>\n" +
                "</html>").as("text/html");
    }

    public Result ajoutScore(String user) {
       RequestBody body = request().body();
       
       int minutes = Integer.parseInt(body.asText().split("=")[1].split(":")[0].trim());
       int secondes = Integer.parseInt(body.asText().split("=")[1].split(":")[1].trim());
       
       String reponse  = String.format("%s ===> %s : %02dmin %02dsec", user, body.asText().split("=")[0].trim(),
                                                                     minutes,
                                                                     secondes);
       if(minutes < 1 && secondes <=20){
           reponse += " ... ca sent la triche !";
       }
       
       reponse += "\n";
       reponse += connection.toString();

       return ok(reponse);
    }
}
