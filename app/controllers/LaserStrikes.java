package controllers;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;

import java.util.LinkedList;

public class LaserStrikes extends Controller {

    Connection connection = DB.getConnection();
    LinkedList<String> fifo;

    LaserStrikes() {
        super();
        this.fifo = new LinkedList<>();
        this.fifo.add("une");
        this.fifo.add("deux");
        this.fifo.add("trois");
    }


    public Result toFifo() {
        RequestBody body = request().body();

        String user = body.asText();

        this.fifo.add(user);



        return ok(afficheFifo());
    }

    private String afficheFifo(){

        String result = "";

        while (this.fifo.peek() != null){
            result += this.fifo.poll() + " <-> ";
        }

        return result;
    }
}