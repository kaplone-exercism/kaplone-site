package controllers;

import java.util.*;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;
//import enums.*;

import java.nio.file.*;
import java.io.*;
import play.twirl.api.Html;
import javax.inject.*;

public class ControleurCV extends Controller {

    @Inject
    private Environment environment;

    public Result afficher(String article) {

//       Articles a = null;
//
//       try {
//           a = Articles.valueOf(article);
//       }
//       catch (IllegalArgumentException iae){
//           a = Articles.valueOf("architecte");
//       }

       // http://www.kaplone.fr/assets/articles/

       return ok(main.render(article,
              // Html.apply(a.getFileContent())));
               Html.apply(new String(Files.readAllBytes(environment.getFile(String.format("/public/articles/%s.html")).toPath())))));

    }
}
