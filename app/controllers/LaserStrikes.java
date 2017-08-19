package controllers;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;

public class LaserStrikes extends Controller {

    Connection connection = DB.getConnection();

    public Result toFifo() {
        RequestBody body = request().body();

        String user = "aucun";

        if (request().headers().get(Http.HeaderNames.CANDIDAT).length > 0){
            user = request().headers().get(Http.HeaderNames.CANDIDAT)[0];
        }

        return ok(user);
    }
}