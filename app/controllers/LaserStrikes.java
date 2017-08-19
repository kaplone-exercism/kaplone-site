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

    static {
        LinkedList<String> fifo = new LinkedList<>();
        fifo.add("une");
        fifo.add("deux");
        fifo.add("trois");
    }


    public Result toFifo() {
        RequestBody body = request().body();

        String user = body.asText();

        fifo.add(user);



        return ok(afficheFifo());
    }

    private String afficheFifo(){

        String result = "";

        while (fifo.peek() != null){
            result += fifo.poll() + " <-> ";
        }

        return result;
    }
}