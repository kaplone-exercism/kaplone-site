package enums;

import java.util.*;

public enum Articles {

    architecte("architecte.html"),
    analyste("analyste.html"),
    avant("avant.html");

    private final String file;

    Articles(String s){
        this.file = s;
    }

    public String getFileName(){
        return this.file;
    }
}