package controllers;

import java.sql.*;
import javax.sql.*;

import play.db.*;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import views.html.*;
import models.*;
import utils.*;

public class ApiNiveaux extends Controller {

    public Result soumissionNiveau(String user) {

        String body = request().body().asText();

        String encodedToken = request().getHeader("token");
        byte [] bytes = encodedToken.getBytes();

        LoadConfig.loadSettings();
        String key = Settings.getKey();

        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encrypted = decoder.decode(bytes);
            String decrypted = new String(cipher.doFinal(encrypted));

            if (Settings.getUsers().contains(decrypted.split(" ")[0])) {
                return ok(decrypted.split(" ")[0] + " est un utilisateur valide");
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "problème avec l'utilisateur ...";
    }

}

