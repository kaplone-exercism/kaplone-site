package controllers;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;
import models.*;

import java.util.LinkedList;


public class LaserStrikes extends Controller {

    Connection connection = DB.getConnection();
    LinkedList<String> fifo;

    LaserStrikes() {
        super();
        this.fifo = new LinkedList<>();
    }


    public Result toFifo() {
        RequestBody body = request().body();

        String user = body.asText();

        String result = "En attente d'un deuxième candidat pour le duel ...";

        if (this.fifo.peek() != null){
            this.fifo.add(user);
            LaserPair lp = new LaserPair(this.fifo.poll(), this.fifo.poll());
            result = lp.toString();
        }
        else {
            this.fifo.add(user);
        }

        return ok(result);
    }
}