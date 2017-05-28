package controllers;

import java.sql.*;
import javax.sql.*;
import java.io.*;

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

    public Result soumissionNiveau() {

        String encodedToken = request().getHeader("monToken");
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

                MultipartFormData<File> body = request().body().asMultipartFormData();

                FilePart<File> picture = body.getFile("picture");
                if (picture != null) {
                    String fileName = picture.getFilename();
                    String contentType = picture.getContentType();
                    File file = picture.getFile();
                }

                String bodyText = body.getFile("text").
                //String bodyText = request().body().asText();

                enregistrerImage(file);

                inclureNiveau(bodyText);

                return ok(decrypted.split(" ")[0] + " est un utilisateur valide\n" + body);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return status(488, "problème avec l'utilisateur ...");
    }

    private void inclureNiveau(String s){

        String home =  System.getProperty("user.home");
        File file = new File(home, "niveaux_.conf");

        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write("\n");
            fw.write(s);
            fw.close();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private void enregistrerImage(File file, String fileName){

        String home =  System.getProperty("user.home");

        File convFile = new File(home + "/captures",filename);
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

    }

    public Result niveaux(){

        String home =  System.getProperty("user.home");
        File file = new File(home, "niveaux_.conf");

        return ok(file);
    }
}

