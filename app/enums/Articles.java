package enums;

import java.util.*;
import java.nio.file.*;
import java.io.*;

public enum Articles {

    architecte("/public/articles/architecte.html"),
    analyste("/public/articles/analyste.html"),
    avant("/public/articles/avant.html");

    private String file;

    Articles(String s){
        this.file = s;
    }

    public String getFileName(){
        return this.file;
    }

    public String getFileContent(){

        String res = "";

        try {
            res = new String(Files.readAllBytes(Paths.get(this.file)));
        }
        catch (IOException ioe){
        }

        System.out.println("Depuis le fichier : " + res);

        return res;
    }
}
