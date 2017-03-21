package enums;

import java.util.*;
import java.nio.file.*;
import java.io.*;

public enum Articles {

    architecte("architecte.html"),
    analyste("analyste.html"),
    avant("avant.html");

    private String file;

    Articles(String s){
        this.file = s;
    }

    public String getFileName(){
        return this.file;
    }

    public String getFileContent(){
        return new String(Files.readAllBytes(Paths.get(this.file)));
    }
}
