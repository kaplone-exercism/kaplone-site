package enums;

import java.nio.file.*;
import java.io.*;

import play.*;

public enum Articles {

    architecte("/public/articles/architecte.html"),
    analyste("/public/articles/analyste.html"),
    avant("/public/articles/avant.html");

    private String file;

    Articles(String s){
        this.file = s;
    }

    public String getFileContent(){

        String res = "";

        try {
            res = new String(Files.readAllBytes(Application.getFile(this.file).toPath()));
        }
        catch (IOException ioe){
        }

        System.out.println("Depuis le fichier : " + res);

        return res;
    }
}
