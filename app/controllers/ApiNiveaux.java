package controllers;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;

public class ApiNiveaux extends Controller {

    public Result soumissionNiveau(String user) {

        String body = request().body().asText();

        String encodedToken = request().getHeader("token");
        byte [] bytes = encodedToken.getBytes();

        LoadConfig.loadSettings();
        String key = Settings.getKey();
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encrypted = decoder.decode(bytes);
        String decrypted = new String(cipher.doFinal(encrypted));

        if (Settings.getUsers().contains(decrypted.split(" ")[0])){
            return ok(decrypted.split(" ")[0]  + " est un utilisateur valide");
        }
    }

}

