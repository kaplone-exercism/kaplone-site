package controllers;

import java.util.*;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;
import enums.*;

import play.twirl.api.Html;

public class ControleurCV extends Controller {

   public Result afficher(String article) {

       Articles a = null;

       try {
           a = Articles.valueOf(article);
       }
       catch (IllegalArgumentException iae){
           a = Articles.valueOf("architecte");
       }

       return ok(main.render(article, Html.apply(a.getFileContent())));

    }
}
