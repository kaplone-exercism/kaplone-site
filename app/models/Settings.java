package models;

import java.util.*;

public class Settings {

    private static List<String> users;
    private static String key;

    public static List<String> getUsers() {
        return users;
    }

    public static void setUsers(String users_) {
        users = new ArrayList<>();
        for (String s : users_.split(",")){
            users.add(s.trim());
        }
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key_) {
        key = key_;
    }
}