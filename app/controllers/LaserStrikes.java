package controllers;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.*;

import views.html.*;
import models.*;


import javax.websocket.*;

import java.util.LinkedList;


public class LaserStrikes extends Controller {

    Connection connection = DB.getConnection();
    LinkedList<String> fifo;
    LaserPair lp;

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
            lp = new LaserPair(this.fifo.poll(), this.fifo.poll());
            result = lp.toString();
        }
        else {
            this.fifo.add(user);
        }

        return ok(result);
    }

    public WebSocket<String> toFifoSocket() {

        return new WebSocket<String>() {
            public void onReady(final WebSocket.In<String> in, final WebSocket.Out<String> out){

                System.out.println("Ready ...");

                in.onMessage(a -> System.out.println(a));
                in.onClose(
                        new CallBack0() {
                            public void invoke() {
                                System.out.println("closed !"));
                            }
                        }
                );
            }
        };
    }
}