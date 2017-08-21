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

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.LinkedList;

import utils.Server;
import utils.RequestHandler;


public class LaserStrikes extends Controller {

    Connection connection = DB.getConnection();
    LinkedList<String> fifo;
    LaserPair lp;

    ServerSocket serverSocket;

    LaserStrikes() {
        super();
        this.fifo = new LinkedList<>();
        Server.setListening(true);
        Server.listen(9021);
        serverSocket = Server.getServerSocket();
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

    public Result toFifoSocket() {

        while(Server.isListeningSocket){
            Socket clientSocket = serverSocket.accept();
            RequestHandler requestHandler = new RequestHandler(clientSocket);
            requestHandler.start();
        }

        return ok(" -> socket");

   }
}