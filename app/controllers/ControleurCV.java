package controllers;

import java.util.*;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;

import java.nio.file.*;
import java.io.*;
import play.twirl.api.Html;
import javax.inject.*;

public class ControleurCV extends Controller {

    @Inject
    private Environment environment;

    public Result afficher(String article) {

        String header;
        String footer;
        String leftMenu;
        String contenuArticle;

        try {
            contenuArticle = new String(Files.readAllBytes(environment.getFile(String.format("/public/articles/%s.html", article)).toPath()));
            header = new String(Files.readAllBytes(environment.getFile("/public/html/top.html").toPath()));
            footer = new String(Files.readAllBytes(environment.getFile("/public/html/bottom.html").toPath()));
            leftMenu = new String(Files.readAllBytes(environment.getFile("/public/html/leftColumn.html").toPath()));
        }
        catch (IOException ioe){
            contenuArticle = "<h1> Erreur </h1>";
            header = "";
            footer = "";
            leftMenu = "";
        }

       return ok(main.render(article,
               Html.apply(header),
               Html.apply(footer),
               Html.apply(leftMenu),
               Html.apply(contenuArticle)));

    }
}
